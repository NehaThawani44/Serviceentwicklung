# To-Do Service

This microservice is designed to manage To-Dos.
Built with Java and Spring Boot, and leveraging the H2 database for persistence, 
it offers a RESTful API for the full lifecycle management of to-dos.

## Features

- **CRUD Operations**: Manage your to-dos with full create, read, update, and delete.
- **Data Persistence**: Utilizes the h2 DB to ensure data is maintained across application restarts.
- **Efficient and Faster**: Built on Spring Boot, known for its minimal complexity for dependencies.
- **Testing Ready**:  Includes unit and integration tests, ensuring reliable operation.
-  **Scripting**: Shell scripts for Unix/Linux and Batch scripts for Windows

## Getting Started

### Prerequisites

- Java JDK 17 
- SpringBoot 2.7.3


### Installation
Note: Master branch has apis using Postgresql, the branch h2_config is only for reference for H2 database config.
1. Clone the repository:
    ```bash
    git clone https://github.com/NehaThawani44/Serviceentwicklung.git
    ```

2. Navigate to the project directory:
    ```bash
    cd task-service
    ```

3. Build the project with Maven:
    ```bash
    mvn clean install
    ```

4. Run the script:
    ```bash
    java -jar target/task-service-0.0.1-SNAPSHOT.jar 
    ```

Now, the service is up and running, ready to manage to-dos.



### Running the Application

After building the project with the provided script, the Spring Boot server will start. You can access the web interface by navigating to http://localhost:8080 in your web browser.

### Using the API

View TODO Entries: Open your browser and go to http://localhost:8080. All current TODO entries will be displayed.
Delete a TODO Entry: Click on a TODO entry on the website to delete it.

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "title": "Send Invite",
  "description": "List out all emails",
  "completionDate": "2024-08-29"
}' http://localhost:8080/todos/
'
		 
curl -X GET 'http://localhost:8080/todos'		 
```

Get all the todos: http://localhost:8080/todos







