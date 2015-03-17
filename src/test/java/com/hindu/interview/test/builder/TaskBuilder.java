package com.hindu.interview.test.builder;

import com.hindu.interview.ws.model.Priority;
import com.hindu.interview.ws.model.Status;
import com.hindu.interview.ws.model.Task;

public class TaskBuilder {
	private Task task = new Task();

	public TaskBuilder id(long id) {
		task.setId(id);
		return this;

	}

	public TaskBuilder description(String desc) {
		task.setDescription(desc);
		return this;
	}

	public TaskBuilder priority(String priority) {
		Priority prty = Priority.valueOf(priority);
		task.setPriority(prty);
		return this;
	}

	public TaskBuilder status(String status) {
		Status st = Status.valueOf(status);
		task.setStatus(st);
		return this;
	}

	public Task build() {
		return task;
	}
}
