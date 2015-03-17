package com.hindu.interview.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hindu.interview.ws.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t WHERE LOWER(t.priority) = LOWER(:priority) AND LOWER(t.status) = LOWER(:status)")
	public List<Task> findByPriorityAndStatus(
			@Param("priority") String priority, @Param("status") String status);

	@Query("SELECT t FROM Task t WHERE LOWER(t.priority) = LOWER(:priority)")
	public List<Task> findByPriority(@Param("priority") String priority);

	@Query("SELECT t FROM Task t WHERE  LOWER(t.status) = LOWER(:status)")
	public List<Task> findByStatus(@Param("status") String status);

}
