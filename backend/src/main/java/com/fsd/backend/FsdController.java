package com.fsd.backend;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class FsdController.
 */

/**
 * @author Prasad Pai M
 *
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/fsdBackend")
public class FsdController {

	private final Logger log = LoggerFactory.getLogger(FsdController.class);
			
	/** The fsd service. */
	@Autowired
	private FsdService fsdService;
		
		
	/**
	 * Adds the task.
	 *
	 * @param reqBody
	 *            the req body
	 * @return the task
	 * @throws FsdException
	 *             the fsd exception
	 */
	@PostMapping("/addTask")
	public Task addTask(@RequestBody FsdJsonModel reqBody) throws FsdException
	{
		Task task = null ;
		
		log.debug("===========================================");
		log.debug("Inside Class Name: FSD Controller ");
		log.debug("Inside Method Name: addTask ");
		log.debug("Task Details to be added:" + reqBody.toString());
		log.debug("===========================================");
		
		try{
			task = convertFsdModeltoTask(reqBody);
			fsdService.saveTask(task);
			 
		}
		catch(Exception e)
		{
			throw new FsdException("Error Adding the Task:" + task.toString() , e);
		}
		
		return  task;
	}
	
	/**
	 * Gets the all task.
	 *
	 * @return the all task
	 * @throws FsdException
	 *             the fsd exception
	 */
	@RequestMapping("/getAllTask")
	public List<Task> getAllTask() throws FsdException
	{
		List<Task> taskList = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: FSD Controller ");
		log.debug("Inside Method Name: getAllTask ");
		log.debug("===========================================");
		
	try {	
		taskList=fsdService.getAllTaks();
	}
	catch(Exception e)
	{
		throw new FsdException("Error Fetching the TaskList from Database:" , e);
	}
		return  taskList;
	}
	
	
	@RequestMapping("/Delete Task")
	public Task deleteTask(@RequestBody Task task) throws FsdException
	{
		Task returned = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: FSD Controller ");
		log.debug("Inside Method Name: Delete Task ");
		log.debug("===========================================");
		
	try {	
		returned=fsdService.deleteTask(task);
	}
	catch(Exception e)
	{
		throw new FsdException("Error Fetching the TaskList from Database:" , e);
	}
		return  returned;
	}
	
	/**
	 * Gets the task by id.
	 *
	 * @param taskId
	 *            the task id
	 * @return the task by id
	 * @throws FsdException
	 *             the fsd exception
	 */
	@RequestMapping("/getTaskById/{taskId}")
	public Task getTaskById(@PathVariable(name="taskId")String taskId ) throws FsdException
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: FSD Controller ");
		log.debug("Inside Method Name: getTaskById ");
		log.debug("Task Id Searched for: " + taskId);
		log.debug("===========================================");
		
		try {
		Optional taskIfExist = fsdService.findById(Long.parseLong(taskId));
		if(taskIfExist != null) 
		return (Task) taskIfExist.get();
		else
		return null;
		}
		catch(Exception e)
		{
			throw new FsdException("Error Fetching the Task with the "+taskId +" from Database:" , e);
		}
	}
	
	/**
	 * Update task.
	 *
	 * @param reqBody
	 *            the req body
	 * @return the task
	 * @throws FsdException
	 *             the fsd exception
	 */
	
	@PutMapping("/updateTask/{taskId}")
	public Task updateTask(@RequestBody FsdJsonModel reqBody,@PathVariable(name="taskId")String taskId) throws FsdException
	{
	
		Task task = null;
		Task convertedTask = convertFsdModeltoTask(reqBody);
		convertedTask.setTaskId(Long.parseLong(taskId));
        log.debug("===========================================");
		log.debug("Inside Class Name: FSD Controller ");
		log.debug("Inside Method Name: updateTask ");
		log.debug("Task Id Searched for: " + reqBody.toString());
		log.debug("===========================================");
		task = processUpdate(task,convertedTask);
		return task;
		}
	
	
	@PutMapping("/updateEndDate")
	public Task updateEndDate(@RequestBody Task reqBody) throws FsdException
	{
	
		Task task = null;
        log.debug("===========================================");
		log.debug("Inside Class Name: FSD Controller ");
		log.debug("Inside Method Name: updateTask ");
		log.debug("Task Id Searched for: " + reqBody.toString());
		log.debug("===========================================");
		task = processUpdate(task,reqBody);
		return task;
	
		}
	
	
	private Task processUpdate(Task task, Task reqBody) throws FsdException {
		try {	
			Optional taskIfExist = fsdService.findById(reqBody.getTaskId());
			if(taskIfExist != null) {
			task = (Task) taskIfExist.get();
			task= updateTaskDetail(task,reqBody);		
			fsdService.saveTask(task);
			return  task;
			}
			return null;
		}
		catch(Exception e)
		{
			throw new FsdException("Error Fetching the Task with the details:"+ reqBody.toString() +" from Database:" , e);
		}
	}

	/**
	 * Update task detail.
	 *
	 * @param task
	 *            the task
	 * @param reqBody
	 *            the req body
	 * @return the task
	 */
	private Task updateTaskDetail(Task task, Task reqBody) {
		
		if(task.getPriority() != reqBody.getPriority())
			task.setPriority(reqBody.getPriority());
		
		if(!task.getParentTask().getParentTask().equals(reqBody.getParentTask().getParentTask()))
			task.getParentTask().setParentTask(reqBody.getParentTask().getParentTask());
		
		if(!task.getTask().equals(reqBody.getTask()))
			task.setTask(reqBody.getTask());
		
		if(!task.getStartDate().equals(reqBody.getStartDate()))
		    task.setStartDate(reqBody.getStartDate());
		
		if(!task.getEndDate().equals(reqBody.getEndDate()))
		    task.setEndDate(reqBody.getEndDate());
		
		return task;
	}

	/**
	 * Convert fsd modelto task.
	 *
	 * @param reqBody
	 *            the req body
	 * @return the task
	 */
	private Task convertFsdModeltoTask(FsdJsonModel reqBody) {
		Task  task = new Task();
		ParentTask parentTask = new ParentTask();
	    parentTask.setParentTask(reqBody.getParentTask());
	    task.setEndDate(LocalDate.parse(reqBody.getEndDate()));
	    task.setStartDate(LocalDate.parse(reqBody.getStartDate()));
	    task.setPriority(reqBody.getPriority());
	    task.setTask(reqBody.getTask());
	    task.setParentTask(parentTask);
		return task;
	}


}
