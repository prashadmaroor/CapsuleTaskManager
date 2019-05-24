import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import { AppComponent } from './app.component';
import { ViewTaskComponent } from './view-task/view-task.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { TaskPipe } from './pipe/task.pipe';
import {CookieService} from 'ngx-cookie-service';
import { UpdateComponentComponent } from './update-component/update-component.component';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
//import {PopupModule} from 'ng2-opd-popup';
//import { ConfirmationPopoverModule } from 'angular-confirmation-popover';

@NgModule({
  declarations: [
    AppComponent,
    ViewTaskComponent,
    EditTaskComponent,
    NavigationBarComponent,
    TaskPipe,
    UpdateComponentComponent
  ],
  imports: 
  [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    // ConfirmationPopoverModule.forRoot({
    //   cancelButtonType: 'danger'
    // }),
    RouterModule.forRoot([
      {
        path:'viewTasks',
        component:ViewTaskComponent
      },
      {
        path:'editTasks',
        component:EditTaskComponent
      },
      {
        path:'tasks/:id',
        component:UpdateComponentComponent
      },
      {
        path:'**',
        component:ViewTaskComponent
      }
    ])
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
