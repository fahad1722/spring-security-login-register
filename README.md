# 🛡️ Spring Boot Security Application
This repository provides a RESTful API for registration and login built with Spring Boot. The API supports two roles: ROLE_USER and ROLE_ADMIN, with specific endpoints for user profile management and admin functionalities.

**Base URL**
http://localhost:8080

**Endpoints:**

**User Registration**

**Endpoint**: POST /register

**Description**: Registers a new customer

**Request Body**: {
  "name": "Jack",
  "email": "jack@gmail.com",
  "password": "jack@123",
  "phoneNumber": 9876543210,
  "role": "ROLE_USER"
}

**Response:**

<img width="629" height="359" alt="image" src="https://github.com/user-attachments/assets/2ebff892-c0ea-4316-80d6-4e7fbb9d9948" />

**Login**

**Endpoint**: POST /login

**Description**: Authenticates a user and returns a success message or token.

**Request Body**:{
  "email": "jack@gmail.com",
  "password": "jack@123"
}


**Response**: 

<img width="621" height="338" alt="image" src="https://github.com/user-attachments/assets/a175ca60-9f62-4b5b-a393-8a215adef953" />


**Get Profile**

**Endpoint**: GET /profile

**Description**: Retrieves the profile details of the authenticated user.

**Authentication**: Basic Auth

**Username**: jack@gmail.com (or admin@gmail.com for admin)

**Password**: jack@123 (or admin@123 for admin)

**Response**:

<img width="632" height="353" alt="image" src="https://github.com/user-attachments/assets/e6eb7a35-a797-4cbb-b98a-2ab5a8b23d91" />

<img width="635" height="348" alt="image" src="https://github.com/user-attachments/assets/daf76f99-f6ec-408f-bc27-f3b81ef8177e" />


**Get Customer Data**

**Endpoint**: GET /customer/data

**Description**: Retrieves data specific to a customer (accessible to users with ROLE_USER).

**Authentication**: Basic Auth

**Username**: jack@gmail.com

**Password**: jack@123


**Response**:

<img width="635" height="301" alt="image" src="https://github.com/user-attachments/assets/6d7ba217-22e6-42ac-8972-58a34cdf303c" />



**Get Admin Data**

**Endpoint**: GET /admin/data

**Description**: Retrieves admin-specific data

**Authentication**: Basic Auth

**Username**: admin@gmail.com

**Password**: admin@123

**Response**:

<img width="632" height="312" alt="image" src="https://github.com/user-attachments/assets/6a11fcd9-845c-4028-af52-e97b174d8103" />


**Get All Customers (Admin Only)**

**Endpoint**: GET /admin/customers

**Description**: Retrieves a list of all registered customers (accessible to users with ROLE_ADMIN).
**Authentication**: Basic Auth

**Username**: admin@gmail.com

**Password**: admin@123

**Response:**

<img width="627" height="335" alt="image" src="https://github.com/user-attachments/assets/313dd721-1f5e-4c5c-94a1-325bec577e5a" />
