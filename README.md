# Task-List Backend API (Java + Spring Boot)

This is a simple backend server built using Java Spring Boot to manage Task Lists and Tasks.  
The project helped me understand core backend concepts such as REST APIs, Spring Data JPA, layered architecture, exception handling, entity mappings, and more.

---

## Features

### TaskList
- Create a new task list  
- Update an existing task list  
- Delete a task list  
- Get all task lists  
- Get a task list by ID  

### Task
- Create a task under a specific task list  
- Update a task  
- Delete a task  
- Get a task by taskListId + taskId  
- Get all tasks inside a task list  

---

## Tech Stack

- Java 17+  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Hibernate  
- MySQL / PostgreSQL  
- Lombok  
- Postman for API testing  

---

## What I Learned

- How @RestController, @Service, and @Repository work together  
- How @Id and @Column map Java fields to database columns  
- CRUD operations using Spring Data JPA without writing SQL  
- Using ResponseEntity to return structured API responses  
- Designing controllers, services, repositories, and DTOs  
- UUID generation for IDs  
- Global exception handling using @ControllerAdvice  
- Mapping Java classes to database tables with JPA  

---

## Project Structure

src/main/java/your/project
│
├── controller
│   ├── TaskListController.java
│   └── TaskController.java
│
├── service
│   ├── TaskListService.java
│   ├── TaskService.java
│   └── impl/
│
├── repository
│   ├── TaskListRepository.java
│   └── TaskRepository.java
│
├── entity (or domain)
│   ├── TaskList.java
│   └── Task.java
│
├── dto
│   ├── TaskListDto.java
│   └── TaskDto.java
│
├── exception
│   └── GlobalExceptionHandler.java
│
└── mapper
    ├── TaskListMapper.java
    └── TaskMapper.java

---

## How to Run the Project

1. Clone the repository:
   git clone https://github.com/<your-username>/<repo-name>.git
   cd <repo-name>

2. Update application.properties:

   spring.application.name=Task-list-app
  spring.datasource.url= jdbc:mysql://localhost:3306/task_list_app
  spring.datasource.username=root
  spring.datasource.password=anurag
  spring.jpa.hibernate.ddl-auto=update

3. Build and run:
   mvn spring-boot:run

Or run directly from your IDE.

---

## API Endpoints

### TaskList APIs
POST    /task-lists  
GET     /task-lists  
GET     /task-lists/{id}  
PUT     /task-lists/{id}  
DELETE  /task-lists/{id}  

### Task APIs
POST    /task-lists/{taskListId}/tasks  
GET     /task-lists/{taskListId}/tasks  
GET     /task-lists/{taskListId}/tasks/{taskId}  
PUT     /task-lists/{taskListId}/tasks/{taskId}  
DELETE  /task-lists/{taskListId}/tasks/{taskId}  

---

## Sample JSON Payloads

### Create Task List
{
  "title": "My First Task List",
  "description": "My initial list for testing"
}

---

### Create Task
{
  "title": "Learn Spring Boot",
  "description": "Understanding controllers and services",
  "priority": "MEDIUM",
  "status": "PENDING",
  "dueDate": "2025-01-10T10:00:00"
}

---

## Global Exception Example

{
  "message": "Task not found",
  "status": 404,
  "timestamp": "2024-01-01T10:00:00"
}

---

## Future Improvements

- JWT Authentication  
- Pagination  
- Soft Delete  
- Swagger Documentation  
- Unit Tests (JUnit + Mockito)  

---

## Contributing

Feel free to fork the repo and submit a pull request.

---

## Star the Repo

If this project helped you, please consider starring the repository!
