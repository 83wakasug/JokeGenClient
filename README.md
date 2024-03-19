# JokeGen Client Application

This repository contains the source code for the JokeGen Client application. The application provides functionality for managing jokes and authors through a web interface. Users can view, create, edit, and delete jokes, as well as manage authors.

## Table of Contents
1. [Installation](#installation)
2. [Usage](#usage)
3. [Endpoints](#endpoints)
4. [Contributing](#contributing)
5. [License](#license)

## Installation <a name="installation"></a>

To run the JokeGen Client application locally, follow these steps:

1. Clone this repository to your local machine.
2. Ensure you have JDK21 and Maven installed.
3. Navigate to the project directory in your terminal.
4. Run `mvn clean install` to build the project and resolve dependencies.
5. Run the application using `mvn spring-boot:run`.
6. Access the application in your web browser at `http://localhost:8080/jokes/login`.

## Usage <a name="usage"></a>

Upon accessing the application, users can perform the following actions:

- **Login:** Users can log in using their credentials or sign up for a new account if they don't have one.
- **View Jokes:** Users can view a list of jokes available in the system.
- **Create Jokes:** Users can add new jokes to the system.
- **Edit Jokes:** Admin users can edit existing jokes.
- **Delete Jokes:** Admin users can delete jokes from the system.
- **View Authors:** Admin users can view a list of authors.
- **Edit Authors:** Admin users can edit author details.
- **Delete Authors:** Admin users can delete authors from the system.
- **View Users:** Admin users can view a list of all users in the system.
- **Edit Users:** Admin users can edit user role.
- **Delete Users:** Admin users can delete users from the system.

## Endpoints <a name="endpoints"></a>

The application exposes the following endpoints:

- `/jokes/login`: Endpoint for user login.
- `/jokes/signup`: Endpoint for user signup.
- `/jokes/logout`: Endpoint for user logout.
- `/jokes/index`: Home page of the application.
- `/jokes/list`: Endpoint for viewing a list of jokes.
- `/jokes/create`: Endpoint for adding new jokes.
- `/jokes/edit/{id}`: Endpoint for editing a specific joke.
- `/jokes/delete/{id}`: Endpoint for deleting a specific joke.
- `/jokes/author`: Endpoint for viewing a list of authors.
- `/jokes/author/edit/{id}`: Endpoint for editing a specific author.
- `/jokes/author/delete/{id}`: Endpoint for deleting a specific author.
- `/jokes/users`: Endpoint for viewing a list of users.
- `/jokes/users/edit/{id}`: Endpoint for editing a specific user.
- `/jokes/users/delete/{id}`: Endpoint for deleting a specific user.

## Contributing <a name="contributing"></a>

Contributions to this project are welcome. You can contribute by reporting bugs, suggesting enhancements, or directly submitting pull requests.

## License <a name="license"></a>

This project is licensed under the [MIT License](LICENSE).