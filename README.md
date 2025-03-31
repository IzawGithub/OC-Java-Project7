# PoseidonCapitalSolutions

PoseidonCapitalSolutions aggregator makes storing assets safe and secure.

## Table of contents

- [PoseidonCapitalSolutions](#poseidoncapitalsolutions)
  - [Table of contents](#table-of-contents)
  - [Running the application](#running-the-application)
    - [Maven](#maven)

## Running the application

There is 1 way to run this application:

### Maven

Run the tests using:

```sh
./mvnw test
```

Run the mutation tests using:

```sh
./mvnw test-compile org.pitest:pitest-maven:mutationCoverage
```

Run the formater using:

```sh
./mvnw spotless:apply
```

Build the application as an uber JAR (a JAR containing all of its dependencies) using:

```sh
./mvnw spring-boot:repackage
java -jar build/**.jar # Run
```

Run the application using:

```sh
cp .env.CHANGEME .env
# Edit the `DATABASE_**` variables to match your PostgreSQL instance
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh # Install Rust, used to install the migration tool
cargo install sqlx-cli
sqlx database setup
./mvnw spring-boot:run
```
