import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import { Task } from 'src/app/model/task';
import {JsonService} from 'src/app/service/json.service';
import {ToastrService} from 'src/app/service/toastr.service';
import {switchMap,tap} from 'rxjs/operators';
import {ActivatedRoute, ParamMap} from "@angular/router";
import { FsdModel } from '../model/FsdModel';


@Component({
  selector: 'app-update-component',
  templateUrl: './update-component.component.html',
  styleUrls: ['./update-component.component.css']
})
export class UpdateComponentComponent implements OnInit {

  data:any;
  updateTaskForm:FormGroup;
  task:Task[] =[];
  taskSelected:String;


  constructor(private activatedRoute:ActivatedRoute,private jsonService:JsonService, private toasterService:ToastrService) { 
    this.activatedRoute.paramMap.pipe(
      switchMap((params: ParamMap) =>
            this.jsonService.getDataByID(params.get('id')))
     ).subscribe((data:any)=>{ 
      this.data=data;  
      this.toasterService.infoMessages("The effects of update operation cannot be reversed!!!") 
      this.updateTaskForm= new FormGroup({
        id:new FormControl(data.taskId,[Validators.required,Validators.pattern('[a-z A-Z 0-9]{1,200}')]),
        task:new FormControl(data.task,[Validators.required,Validators.pattern('[a-z A-Z 0-9]{1,200}')]),
        priority: new FormControl(data.priority,[Validators.required,Validators.pattern('[0-9]{1,2}')]),
        parentTask:new FormControl(data.parentTask.parentTask),
        startDate:new FormControl(new Date(data.startDate),Validators.required),
        endDate:new FormControl(new Date(data.endDate),Validators.required)
       
      })      
      this.taskSelected = data.parentTask.parentTask;
      }
    );

    this.updateTaskForm=new FormGroup({
      id:new FormControl(''),
      task:new FormControl('',[Validators.required,Validators.pattern('[a-z A-Z 0-9]{1,200}')]),
      priority: new FormControl(0,[Validators.required,Validators.pattern('[0-9]{1,2}')]),
      parentTask:new FormControl(""),
      startDate:new FormControl('',Validators.required),
      endDate:new FormControl('',Validators.required)
     
    })
  }



  ngOnInit() {
 
    let observable=this.jsonService.getData();
     observable.subscribe(
       (data:Task[])=>{
       this.task=data;
     }
     )

  }

  formHandler(){
     this.updateData(this.updateTaskForm.value);
   }
 
  
   updateData(data:any)
   {
    var taskFinal;
    
    data.startDate = data.startDate.toISOString().slice(0,10);
    data.endDate = data.endDate.toISOString().slice(0,10);
    if(data.startDate > data.endDate)
    this.toasterService.deleteMessages("Aborting Task Updation!! End Date cannot be before Start Date");
    else{ 
    console.dir(data);
     if(!data.parentTask.task)
     taskFinal = new FsdModel(data.task,data.priority,data.parentTask,data.startDate,data.endDate);
     else
     taskFinal = new FsdModel(data.task,data.priority,data.parentTask.task,data.startDate,data.endDate);
     
     this.jsonService.updateTask(taskFinal,data.id).subscribe(
       (data)=>{
         this.toasterService.successMessages("Task" + data.task  +"has been successfully updated");
       },
       (error)=>{
         this.toasterService.deleteMessages(" Update of Task failed with Error Code:", error.status);
       } )
      }
   }
  

   printOutput(object:any)
    {
     (<HTMLInputElement>document.getElementById("put")).value = object.value;
    }

}
