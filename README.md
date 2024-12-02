# Advent of Code 2024

This project is a Java implementation of the Advent of Code challenges for the year 2024. Each day's challenge is implemented as a separate class, allowing for organized and modular code.

## Project Structure

```
advent-of-code-2024
├── .gitignore
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── example
    │               └── adventofcode
    │                   ├── App.java
    │                   └── days
    │                       ├── Day01.java
    │                       ├── Day02.java
    │                       ├── Day03.java
    │                       └── ...
    └── test
        └── java
            └── com
                └── example
                    └── adventofcode
                        ├── AppTest.java
                        └── days
                            ├── Day01Test.java
                            ├── Day02Test.java
                            ├── Day03Test.java
                            └── ...
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven

### Building the Project

To build the project, navigate to the root directory of the project and run:

```
mvn clean install
```

This command will compile the source code, run tests, and package the application.

### Running the Application

To run the application, execute the following command:

```
mvn exec:java -Dexec.mainClass="com.example.adventofcode.App"
```

### Challenges

Each day's challenge is implemented in its respective class located in the `src/main/java/com/example/adventofcode/days` directory. You can find the solutions for each day in the corresponding `DayXX.java` files.

### Testing

Unit tests for the application can be found in the `src/test/java/com/example/adventofcode` directory. To run the tests, use the following command:

```
mvn test
```

## Contributing

Feel free to fork the repository and submit pull requests for improvements or additional challenges.

## License

This project is licensed under the MIT License. See the LICENSE file for details.