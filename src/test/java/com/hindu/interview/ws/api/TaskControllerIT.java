package com.hindu.interview.ws.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hindu.interview.Application;
import com.hindu.interview.test.builder.TaskBuilder;
import com.hindu.interview.ws.model.Task;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TaskControllerIT {

	private static final String DESCRIPTION_FIELD = "description";

	private static final String FIRST_DESC = "first task description";
	private static final String FIRST_PRIORITY = "LOW";
	private static final String FIRST_STATUS = "NEW";
	private static final long SECOND_ID = 2L;
	private static final String SECOND_DESC = "second task description";
	private static final String SECOND_PRIORITY = "MEDIUM";
	private static final String SECOND_STATUS = "PENDING";
	private static final long THIRD_ID = 3L;
	private static final String THIRD_DESC = "third task description";
	private static final String THIRD_PRIORITY = "HIGH";
	private static final String THIRD_STATUS = "COMPLETE";

	private static final Task FIRST_TASK = new TaskBuilder()
			.description(FIRST_DESC).priority(FIRST_PRIORITY)
			.status(FIRST_STATUS).build();
	private static final Task SECOND_TASK = new TaskBuilder().id(SECOND_ID)
			.description(SECOND_DESC).priority(SECOND_PRIORITY)
			.status(SECOND_STATUS).build();
	private static final Task THIRD_TASK = new TaskBuilder().id(THIRD_ID)
			.description(THIRD_DESC).priority(THIRD_PRIORITY)
			.status(THIRD_STATUS).build();
	private static final String TASK_RESOURCES = "/api/tasks";

	@Test
	public void testCreateTask() {
		given().contentType(ContentType.JSON).body(FIRST_TASK).when()
				.post(TASK_RESOURCES).then().statusCode(201);
	}

	@Test
	public void testGetTasks() {
		given().contentType(ContentType.JSON).when().get(TASK_RESOURCES).then()
				.statusCode(200)
				.body(DESCRIPTION_FIELD, hasItems("task One", "task Two"));

	}

	@Test
	public void testUpdateTasks() {
		given().contentType(ContentType.JSON).body(SECOND_TASK).expect()
				.body(DESCRIPTION_FIELD, equalTo(SECOND_DESC)).when()
				.put(TASK_RESOURCES + "/2");

	}

	@Test
	public void testDeleteTasks() {
		given().contentType(ContentType.JSON).body(THIRD_TASK).expect()
				.statusCode(204).when().delete(TASK_RESOURCES + "/3");

	}
}
