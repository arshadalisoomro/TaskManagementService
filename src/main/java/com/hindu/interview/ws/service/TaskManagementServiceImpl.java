package com.hindu.interview.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hindu.interview.ws.model.Task;
import com.hindu.interview.ws.repository.TaskRepository;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {
	@Autowired
	private TaskRepository repository;

	@Override
	public Collection<Task> listTasks(String priority, String status) {
		if (isEmptyOrNull(priority) && isEmptyOrNull(status)) {
			return repository.findAll();
		} else if (!isEmptyOrNull(priority) && isEmptyOrNull(status)) {
			return repository.findByPriority(priority);
		} else if (isEmptyOrNull(priority) && !isEmptyOrNull(status)) {
			return repository.findByStatus(status);
		} else {
			return repository.findByPriorityAndStatus(priority, status);
		}
	}

	private boolean isEmptyOrNull(String priority) {

		return priority == null || priority.isEmpty();
	}

	@Override
	public Task find(Long id) {
		Task task = repository.findOne(id);
		return task;
	}

	@Override
	public Task create(Task task) {
		if (task.getId() == null) {
			// cannot persist without PK
			return null;
		}
		Task savedTask = repository.save(task);
		return savedTask;
	}

	@Override
	public Task update(Task task) {
		// find if it is present in db already
		Task taskInDatabase = repository.findOne(task.getId());
		if (taskInDatabase == null) {
			// cannot update a task that is not stored already
			return null;
		}
		Task savedTask = repository.save(task);
		return savedTask;
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);

	}

}
