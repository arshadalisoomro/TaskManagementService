package com.hindu.interview.ws.service;

import java.util.Collection;

import com.hindu.interview.ws.model.Task;

public interface TaskManagementService {

	Task find(Long id);

	Task create(Task task);

	Task update(Task task);

	void delete(Long id);

	Collection<Task> listTasks(String priority, String status);
}
