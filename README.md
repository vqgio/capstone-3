# EASY SHOP - CAPSTONE 3 -

Full-Stack Application

## Overview

capstone-3 is a full-stack web application developed as the final project for the LTCA program. It features a Java Spring Boot backend and a JavaScript frontend, offering user registration, authentication, and an e-commerce experience including product browsing, cart management, and user profiles.

## Features

- **User Authentication & Authorization**: Secure login and registration using JWT-based authentication.
- **Profile Management**: Users can create and update their profiles (name, phone, email, address, etc.) --IN PROGRESS--
- **Product Catalog**: Browse products by category, color, and price range, with filtering options.
- **Shopping Cart**: Add, view, and clear cart items; manage shopping sessions --IN PROGRESS--
- **Admin Features**: (If implemented) Admin users can manage products and categories.
- **Responsive UI**: Frontend built with JavaScript for dynamic interactions.

## Technologies Used

- Java 17+ (Spring Boot)
- MySQL (via JDBC and DAOs)
- JWT for security
- JavaScript (ES6+), Axios, Mustache.js for templates
- HTML/CSS (assumed from structure)

## Installation

### Backend

1. Clone the repository:
   ```bash
   git clone https://github.com/vqgio/capstone-3.git
   cd capstone-3/backend
   ```
2. Configure application properties (database URL, JWT secret, etc.) in `src/main/resources/application.properties`.
3. Build and run with your preferred IDE or:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Ensure MySQL is running and database is set up.

### Frontend

1. Open `frontend/index.html` in your browser, or serve via a local server.
2. Update API base URLs in frontend JS config if needed.

## Usage

- Register a new user or log in with existing credentials.
- Browse products, filter by category, color, or price.
- Add products to cart and proceed to checkout.
- Access and edit your profile information.

## Requirements

- Java 17 or higher
- MySQL (configured in backend)
- Node.js (optional, for frontend development tools)
- Modern web browser

## Contributing

Contributions are welcome! Please open issues or pull requests for suggestions and improvements.

## License

This project currently does not have a license. Add one if desired.

## Contact

For questions, reach out via [https://github.com/vqgio](https://github.com/vqgio).

