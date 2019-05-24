import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RequestOptions , Headers} from '@angular/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class JsonService {
  
  JSON_URL:string =  'http://localhost:8080/v1/fsdBackend';

  constructor(private httpClient:HttpClient) { }

  getData():Observable<any>{
    let observables=this.httpClient.get(this.JSON_URL +"/getAllTask");
     return observables;
    }

  updateEndDate(data:any):Observable<any>
  {
  // let cpHeaders = new Headers({ 'Content-Type': 'application/json' });

  // const httpOptions = {  headers: new HttpHeaders({ 'Content-Type':  'application/json', }) }
  let observables= this.httpClient.put(this.JSON_URL+  '/updateEndDate' , data);
  return observables;    
}


updateTask(data:any,id:string):Observable<any>
{
let observables= this.httpClient.put(this.JSON_URL+  '/updateTask/'+ id  , data);
return observables;    
}

deleteTask(data:any):Observable<any>
{
let observables= this.httpClient.post(this.JSON_URL+  '/Delete Task', data);
return observables;    
}

insertData(data:any):Observable<any>{
  let observables = this.httpClient.post(this.JSON_URL+"/addTask",data)
  return observables;
}

getDataByID(id:string):Observable<any>{
  let observables=this.httpClient.get(this.JSON_URL + '/getTaskById/' + id);
  return observables;
}

}
