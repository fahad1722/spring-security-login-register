package com.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.LoginRequest;
import com.security.entity.Customer;
import com.security.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
	boolean isSaved = customerService.saveCustomer(customer);
	if (isSaved) {
	    return ResponseEntity.ok("Customer registered successfully");
	} else {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
	}
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginRequest loginRequest) {
	UsernamePasswordAuthenticationToken authToken =
		new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
	
	Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

	boolean isAuthenticated = authentication.isAuthenticated();
	if (isAuthenticated) {
	    return ResponseEntity.ok("Login successful!! WELCOME!!");
	} else {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
	}
    }
    
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	Customer customer = customerService.findByEmail(email);
	return ResponseEntity
		.ok(Map.of("name", customer.getName(), "email", customer.getEmail(), "role", customer.getRole()));
    }
    
    @GetMapping("/admin/data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminData() {
	return ResponseEntity.ok("Sensitive admin data");
    }

    @GetMapping("/customer/data")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity.ok("Customer data");
    }
    
    @GetMapping("/admin/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving customers: " + e.getMessage());
        }
    }
}
