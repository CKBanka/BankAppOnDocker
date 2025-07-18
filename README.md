## Running the Application

Follow these steps to build and run the full stack application using Docker:

1. **Build the Backend**
    ```bash
    cd ./BankAppOnDocker/Backend
    mvn package
    ```
    This command navigates to the backend directory and packages the Java application using Maven.

2. **Build the Frontend**
    ```bash
    cd ../frontend
    npm install
    npm run build
    ```
    These commands move to the frontend directory, install the required Node.js dependencies, and build the production-ready frontend assets.

3. **Start the Application with Docker Compose**
    ```bash
    cd ..
    docker-compose up --build
    ```
    This command returns to the main project directory and starts all services (backend and frontend) using Docker Compose, building images if necessary.

> **Note:** Ensure Docker, Maven, and Node.js are installed on your system before running these commands.

