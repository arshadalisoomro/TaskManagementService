package com.hindu.interview.ws.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hindu.interview.test.builder.TaskBuilder;
import com.hindu.interview.ws.model.Task;
import com.hindu.interview.ws.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskManagementServiceImplTest {

	@Mock
	private TaskRepository mockRepository;

	@InjectMocks
	private TaskManagementServiceImpl service;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testListTasks() {
		Task task1 = new TaskBuilder().id(1).build();
		Task task2 = new TaskBuilder().id(2).build();
		Task task3 = new TaskBuilder().id(3).priority("HIGH").build();
		Task task4 = new TaskBuilder().id(4).priority("LOW").build();
		Task task5 = new TaskBuilder().id(5).status("NEW").build();
		Task task6 = new TaskBuilder().id(6).status("PENDING").build();
		Task task7 = new TaskBuilder().id(7).priority("HIGH").status("NEW")
				.build();
		Task task8 = new TaskBuilder().id(8).priority("LOW").status("COMPLETE")
				.build();

		Mockito.when(mockRepository.findAll()).thenReturn(
				Arrays.asList(task1, task2, task3, task4, task5, task6, task7,
						task8));

		Mockito.when(mockRepository.findByPriority("HIGH")).thenReturn(
				Arrays.asList(task3, task7));

		Mockito.when(mockRepository.findByStatus("NEW")).thenReturn(
				Arrays.asList(task5, task7));

		Mockito.when(mockRepository.findByPriorityAndStatus("HIGH", "NEW"))
				.thenReturn(Arrays.asList(task7));

		// both priority and status being null
		Collection<Task> task = service.listTasks(null, null);
		assertEquals(8, task.size());
		task = null;

		task = service.listTasks("HIGH", "");
		assertEquals(2, task.size());
		task = null;

		task = service.listTasks(null, "NEW");
		assertEquals(2, task.size());
		task = null;

		task = service.listTasks("HIGH", "NEW");
		assertEquals(1, task.size());
		task = null;
	}

	@Test
	public void testCreate() {

		Task task1 = new TaskBuilder().id(1).build();
		Mockito.when(mockRepository.save(task1)).thenReturn(task1);

		Task savedTask = service.create(task1);
		assertEquals(task1.getId(), savedTask.getId());

		Task task2 = new TaskBuilder().priority("LOW").build();
		Mockito.when(mockRepository.save(task2)).thenReturn(task2);

		Task savedTask2 = service.create(task2);
		assertEquals(null, savedTask2);

	}

	@Test
	public void testUpdate() {
		Task task1 = new TaskBuilder().id(2).build();
		Mockito.when(mockRepository.findOne(task1.getId())).thenReturn(null);

		Task updatedTask = service.update(task1);
		assertEquals(null, updatedTask);

		Task task2 = new TaskBuilder().id(3).build();
		Mockito.when(mockRepository.findOne(task2.getId())).thenReturn(task2);

		Mockito.when(mockRepository.save(task2)).thenReturn(task2);

		updatedTask = service.update(task2);
		assertEquals(task2.getId(), updatedTask.getId());

	}

}
