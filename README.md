# Ministore API

## Introduction
Welcome to the Ministore API project! The goal of this project is to serve as a learning experience where I can enhance my development skills while acquiring a new language.

## Table of Contents
- [Ministore API](#ministore-api)
  - [Introduction](#introduction)
  - [Technologies Used](#technologies-used)
  - [Installation](#installation)
  - [Testing](#testing)
  - [Usage](#usage)
  - [Contributing](#contributing)
  
## Technologies Used
- Language: Java
- Framework: Spring Boot
- Tools: Docker, Docker Compose

## Installation
To install and run this project locally, follow these steps:

1. Clone the repository:
```bash
$  git clone https://github.com/ricardotulio/ministore-api.git
```

2. Navigate to the project directory:
```bash
$ cd ministore-api
```

3. Install dependencies:
```bash
$ ./mvnw install -DskipTests
```

## Usage
To use this project, follow these steps:

1. Run the application:
```bash
$ docker-compose up -d
```

2. Open your browser and navigate to:
```url
http://localhost:8080
```

## Testing
To test this project, run the following command:

```bash
$ docker exec -it app ./mvnw clean test
```

## Contributing
Contributions are welcome! To contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch
```bash
$ git checkout -b feature/feature-name
```
3. Make your changes and commit them:
```bash
$ git commit -m 'Add some feature'
```
4. Push to the branch:
```bash
$ git push origin feature/feature-name
```
5. Create a new Pull Request.

