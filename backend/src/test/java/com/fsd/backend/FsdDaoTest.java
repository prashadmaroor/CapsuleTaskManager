package com.fsd.backend;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableTransactionManagement
public class FsdDaoTest {
	
	private final Logger log = LoggerFactory.getLogger(FsdDaoTest.class);
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private FsdRepo testrepo; 
	
	public Task createTestTask()
	{

		Task task = new Task();
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("Parent-Test-Task");
		task.setEndDate(LocalDate.parse("2019-08-16"));
		task.setStartDate(LocalDate.parse("2019-08-01"));
		task.setPriority(30);
		task.setTask("Test-Task");
		return task;
	}
	
	@Test
	@Rollback(false)
	public void testAddTask()
	{
		Task testTask= createTestTask();
		Task ouptut = testrepo.save(testTask);
		System.out.println("************************************************************************************");
		System.out.println("====================================================================================");
		System.out.println("Junit #1: Junit DAO Test for add Task");
		System.out.println("====================================================================================");
		System.out.println("====================================================================================");
		System.out.println("Details of the Task added:" + testTask.toString());
		System.out.println("====================================================================================");
		assertEquals(ouptut, testTask);
	}
	
	@Test
	@Rollback(false)
	public void testRetrieveAllTask()
	{
		Task testTask= createTestTask();
		em.persist(testTask);
		Iterable<Task> ouptut = testrepo.findAll();
		List<Task> taskList = Lists.newArrayList(ouptut);
		System.out.println("************************************************************************************");
		System.out.println("====================================================================================");
		System.out.println("Junit #2: Junit DAO Test for Get All the Task");
		System.out.println("====================================================================================");
		System.out.println("====================================================================================");
		System.out.println("TaskList:" + taskList.toString());
		System.out.println("====================================================================================");
	}

	@Test
	@Rollback(false)
	public void testRetrieveTaskById()
	{
		Optional<Task> task = testrepo.findById(1L);
		System.out.println("************************************************************************************");
		System.out.println("====================================================================================");
		System.out.println("Junit #3: Junit DAO Test for Get the Task by Id");
		System.out.println("====================================================================================");
		System.out.println("====================================================================================");
		System.out.println("Task Retrieved:" + task.toString());
		System.out.println("====================================================================================");
	}
	
}
