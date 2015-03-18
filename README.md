# TaskManagementService
A Restful Service to maintain a TODO list

This service is aimed to store and retrieve list of tasks. 
Task is defined with a description, priority (LOW,MEDIUM,HIGH) and status (NEW, PENDING, COMPLETE)
It currently supports 
  1. List all task 
  2. List all task with a given priority or status or both
  3. Add a task 
  4. Update a task 
  5. Delete a task 

This is developed using spring boot. Upon cloning, use 
"mvn spring-boot:run" to test the application. 
To execute integration test suites, use "mvn integration-test  -P integration"

