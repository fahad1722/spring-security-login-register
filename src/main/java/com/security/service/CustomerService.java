package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.entity.Customer;
import com.security.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public boolean saveCustomer(Customer customer) {
	String encodedPassword = passwordEncoder.encode(customer.getPassword());
	customer.setPassword(encodedPassword);
	Customer savedCustomer = customerRepository.save(customer);
	return savedCustomer != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Customer customer = customerRepository.findByEmail(email);
	List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
	return new User(
		customer.getEmail(), 
		customer.getPassword(), 
		authorities
	);
    }
    public Customer findByEmail(String email) {
	return customerRepository.findByEmail(email);
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
