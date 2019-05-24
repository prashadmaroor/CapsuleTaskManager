import { Pipe, PipeTransform } from '@angular/core';

export interface ITask {
  task: string;
  priority: number;
  parentTask: any;
  startDate: string;
  endDate: string;
}
@Pipe({
  name: 'task'
})
export class TaskPipe implements PipeTransform {

  // transform(value: any, args?: any): any {
    
  //   console.dir(args);

  //   if(!args)
  //   return value
  //   else
  //   return value.filter(result =>{ 
  //     console.log("==========================");
  //     console.log(result.task);
  //     console.log("==========================");
  //     return result.task.toUpperCase().startsWith(args.toUpperCase()) == true})
      
  // }

  transform
  (
    tasks: ITask[],
    taskSearch?: string,
    priorityFromSearch?: number,
    priorityToSearch?: number,
    parentTaskSearch?: string,
    startDateSearch?: Date,
    endDateSearch?:Date
  ): ITask[] {

    if (!tasks) return [];

    if (taskSearch)
    {
      taskSearch = taskSearch.toLocaleLowerCase();
      tasks = [...tasks.filter(task => task.task.toLocaleLowerCase().includes(taskSearch))];
    }

    if (parentTaskSearch)
    {
      parentTaskSearch = parentTaskSearch.toLocaleLowerCase();
      tasks = [...tasks.filter(task => task.parentTask.parentTask.toLocaleLowerCase().includes(parentTaskSearch))];
    }

    if (startDateSearch)
    {
      tasks = [...tasks.filter(task => task.startDate.includes(startDateSearch.toISOString().slice(0,10)))];
    }

    if (endDateSearch)
    {
      tasks = [...tasks.filter(task => task.endDate.includes(endDateSearch.toISOString().slice(0,10)))];
    }

    if (priorityFromSearch)
    {
      tasks = [...tasks.filter(task => task.priority >= priorityFromSearch)];
    }

    if (priorityToSearch)
    {
      tasks = [...tasks.filter(task => task.priority <= priorityToSearch)];
    }

    return tasks;
  }

}
