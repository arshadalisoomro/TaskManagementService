package com.hindu.interview.ws.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hindu.interview.ws.model.Task;
import com.hindu.interview.ws.service.TaskManagementService;

@RestController
public class TaskController {

	@Autowired
	private TaskManagementService service;

	@RequestMapping(value = "api/tasks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
		Task task = service.find(id);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@RequestMapping(value = "api/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Task>> getTask(
			@RequestParam(value = "priority", required = false) String priority,
			@RequestParam(value = "status", required = false) String status) {
		Collection<Task> tasks = service.listTasks(priority, status);

		if (tasks == null) {
			return new ResponseEntity<Collection<Task>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(value = "api/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		Task savedTask = service.create(task);
		return new ResponseEntity<Task>(savedTask, HttpStatus.CREATED);
	}

	@RequestMapping(value = "api/tasks/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> updateTask(@RequestBody Task task) {
		Task savedTask = service.update(task);
		if (savedTask == null) {
			return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Task>(savedTask, HttpStatus.OK);
	}

	@RequestMapping(value = "api/tasks/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> deleteTask(@PathVariable Long id,
			@RequestBody Task task) {
		service.delete(id);

		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);

	}
}
