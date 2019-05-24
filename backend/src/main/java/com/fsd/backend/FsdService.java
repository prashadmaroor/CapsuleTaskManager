/*
 * 
 */
package com.fsd.backend;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The Class FsdService.
 * 
 * @author Prasad Pai M
 *
 */
@Service
@Transactional
public class FsdService {

	private final Logger log = LoggerFactory.getLogger(FsdService.class);
	
	
	/** The fsd repo. */
	private final FsdRepo fsdRepo;
	
	/**
	 * Instantiates a new fsd service.
	 *
	 * @param fsdRepo
	 *            the fsd repo
	 */
	public FsdService(FsdRepo fsdRepo) {
		this.fsdRepo = fsdRepo;
	}
	
	/**
	 * Save task.
	 *
	 * @param task
	 *            the task
	 * @return the task
	 */
	public Task saveTask(Task task) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Fsd Service ");
		log.debug("Inside Method Name: saveTask ");
		log.debug("===========================================");
		
		Task i = fsdRepo.save(task);
		return i;
	}
	
	/**
	 * Find by id.
	 *
	 * @param Id
	 *            the id
	 * @return the optional
	 */
	public Optional<Task> findById(Long Id) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Fsd Service ");
		log.debug("Inside Method Name: findById ");
		log.debug("===========================================");
		Optional<Task> task = fsdRepo.findById(Id);
		return task;
	}

	/**
	 * Gets the all tasks.
	 *
	 * @return the all tasks
	 */
	public List<Task> getAllTaks()
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: Fsd Service ");
		log.debug("Inside Method Name: getAllTaks ");
		log.debug("===========================================");
		Iterable<Task> task = fsdRepo.findAll();
		List<Task> taskList = Lists.newArrayList(task);
		return taskList;
	}
	
	/**
	 * Deletes the deleted tasks.
	 *
	 * @return the deleted tasks
	 */

	public Task deleteTask(Task task) {
		// TODO Auto-generated method stub
		log.debug("===========================================");
		log.debug("Inside Class Name: Fsd Service ");
		log.debug("Inside Method Name: deleteTask ");
		log.debug("===========================================");
		fsdRepo.delete(task);
		return task;
	}

	
	
}
