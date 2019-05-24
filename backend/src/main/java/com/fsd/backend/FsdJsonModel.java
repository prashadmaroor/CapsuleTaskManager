/*
 * 
 */
package com.fsd.backend;

/**
 * The Class FsdJsonModel.
 *
 * @author Prasad Pai M
 */
public class FsdJsonModel {
	
	/** The task. */
	private String task;
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The priority. */
	private int priority;
	
	/** The parent task. */
	private String parentTask;
	
	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * Sets the task.
	 *
	 * @param task
	 *            the new task
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Sets the priority.
	 *
	 * @param priority
	 *            the new priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/**
	 * Gets the parent task.
	 *
	 * @return the parent task
	 */
	public String getParentTask() {
		return parentTask;
	}
	
	/**
	 * Sets the parent task.
	 *
	 * @param parentTask
	 *            the new parent task
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FsdJsonModel [task=" + task + ", startDate=" + startDate + ", endDate=" + endDate + ", priority="
				+ priority + ", parentTask=" + parentTask + "]";
	}
	
	

}
