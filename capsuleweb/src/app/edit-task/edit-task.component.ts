import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import { Task } from 'src/app/model/task';
import {JsonService} from 'src/app/service/json.service'
import {ToastrService} from 'src/app/service/toastr.service'
import {CookieService} from 'ngx-cookie-service'

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {

  addTaskForm:FormGroup;
  taskSelected:any;
  task:Task[] =[];
 
   ngOnInit() {
     
    // this.task=[
     //  {id: 1, valueString:"TEST"}
     // ]
 
     let observable=this.jsonService.getData();
     observable.subscribe(
       (data:Task[])=>{
       this.task=data;
     }
     )
      
   
    }
 
   constructor(private jsonService:JsonService, private toasterService:ToastrService , private cookieService :CookieService) { 
 
     this.addTaskForm=new FormGroup({
       task:new FormControl('',[Validators.required,Validators.pattern('[a-z A-Z 0-9]{1,200}')]),
       priority: new FormControl(0,[Validators.required,Validators.pattern('[0-9]{1,2}')]),
       parentTask:new FormControl(""),
       startDate:new FormControl('',Validators.required),
       endDate:new FormControl('',Validators.required)
      
     })

     this.taskSelected = new Task(0,"--Please choose the value from dropdown--",0,null,null,null);
     
     
   }
 
 
   formHandler(){
      this.insertData(this.addTaskForm.value);
    }
  
    insertData(data:any){
      
      var taskFinal;
      var taskId: number;
      data.startDate = data.startDate.toISOString().slice(0,10);
      data.endDate = data.endDate.toISOString().slice(0,10);
      if(data.startDate > data.endDate)
      this.toasterService.deleteMessages("Aborting Task Creation!! End Date cannot be before Start Date");
      else{ 
      if(!data.parentTask || data.parentTask.task == "--Please choose the value from dropdown--" )
      taskFinal = new Task(taskId,data.task,data.priority,"This Task has no parent",data.startDate,data.endDate);
      else
      taskFinal = new Task(taskId,data.task,data.priority,data.parentTask.task,data.startDate,data.endDate);
      this.resetForm();
      this.jsonService.insertData(taskFinal).subscribe(
        (data)=>{
          this.toasterService.successMessages("Task Details for *" + data.task  +"* has been successfully registered");
        },
        (error)=>{
          this.toasterService.deleteMessages(" Addition of Task failed with Error Code:", error.status);
        } )

      
      }

    }
   
    setOutput(object:any)
    {
     (<HTMLInputElement>document.getElementById("put")).value = object.value;
    }

    resetForm(){
      this.addTaskForm.reset();
      this.taskSelected = new Task(0,"--Please choose the value from dropdown--",0,null,null,null);
     
    }

}
