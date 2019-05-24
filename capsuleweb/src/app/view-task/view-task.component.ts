import { Component, OnInit } from '@angular/core';
import { Task } from '../model/task';
import { JsonService } from '../service/json.service';
import { ToastrService } from '../service/toastr.service';
import { TaskPipe } from '../pipe/task.pipe';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {
   
  currentDate:string; 
  data:Task[]=[];

  constructor(private jsonService:JsonService, private toastrService:ToastrService) { }

  ngOnInit() 
  {
    this.currentDate = new Date().toISOString().slice(0,10);
    this.loadData();
  }

  loadData()
  {
    let observable=this.jsonService.getData();
    observable.subscribe
    (
      (data:Task[])=>
      {
          this.data=data;
      },
    )
  }


  updateEndDate(data:any)
  { 
    var temp:any;
    if(new Date() < new Date(data.startDate))
    {
      this.toastrService.deleteMessages("Task with a future Start Date cannot be terminated");
    }
    else
    {
      data.endDate = new Date().toISOString().slice(0,10);  
      this.jsonService.updateEndDate(data).subscribe(
          (data)=>{
                    this.toastrService.successMessages("Task : " + data.task  +" has been successfully ended");
                  },
        (error)=>{
                    this.toastrService.deleteMessages("Error Ending Task:" + data.task + "!!! Please Try again");
                  } )
    }

  }

  deleteTask(data:any)
  { 
    this.jsonService.deleteTask(data).subscribe(
      (data)=>{
                this.toastrService.successMessages("Task : " + data.task  +" has been successfully deleted");
                this.loadData();
              },
    (error)=>{
                this.toastrService.deleteMessages("Error Deleting Task:" + data.task + "!!! Please Try again");
              } )

  }

}
