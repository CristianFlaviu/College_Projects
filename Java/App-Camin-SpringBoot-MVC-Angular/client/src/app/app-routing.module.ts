import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TableMaterialComponent } from './table-material/table-material.component';
import { RoomComponent } from './general/room/room.component';
import { RegisterFormComponent } from './general/register-form/register-form.component';
import { AllUsersComponent } from './general/all-users/all-users.component';
import { HomeAdminComponent } from './general/home-admin/home-admin.component';
import { LoginComponent } from './general/login/login.component';
import { AuthGuardService } from './general/auth-guard.service';
import { CanActivate } from '@angular/router';
import { RoleGuardService } from './general/role-guard.service';
import { AnnouncemnetsComponent } from './general/announcemnets/announcemnets.component';
import { AnnouncementDetailsComponent } from './general/announcement-details/announcement-details.component';
import { LogoutComponent } from './general/logout/logout.component';
import { WaterMachineAppComponent } from './general/water-machine-app/water-machine-app.component';
import { ResetPasswordComponent } from './general/reset-password/reset-password.component';
import { RequestChangePassComponent } from './general/request-change-pass/request-change-pass.component';
import { CreateReportComponent } from './general/create-report/create-report.component';
import { ReportDetailsComponent } from './general/report-details/report-details.component';
import { RoomDetailsComponent } from './general/room-details/room-details.component';


const routes: Routes = [

  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "login", component: LoginComponent },

  { path: "home", component: HomeAdminComponent },

  {
    path: "rooms",
    component: RoomComponent,
    canActivate: [AuthGuardService, RoleGuardService],
    data: {expectedRole: ['admin']
    }
  },

  { path: "register", component: RegisterFormComponent },

  {
    path: "users", component: AllUsersComponent,
    canActivate: [AuthGuardService, RoleGuardService],
    data: {expectedRole: ['admin']
    }
  }
  ,
  {
    path: "announcements", component: AnnouncemnetsComponent,
    canActivate:[AuthGuardService,RoleGuardService],
    data:{expectedRole:['admin','user']}
  }
  ,
  {
    path: 'announcement/:id', component: AnnouncementDetailsComponent,
    canActivate:[AuthGuardService,RoleGuardService],
    data:{expectedRole:['admin','user']}
  }
  ,
  {
  path:"report/:id",component:ReportDetailsComponent
  }
  ,
  {
    path:"room/:id",component:RoomDetailsComponent
    }
  ,
  {
    path: "logout", component: LogoutComponent,
    canActivate:[AuthGuardService,RoleGuardService],
    data:{expectedRole:['admin','user']}
  }
  ,
  {
    path:"wmapp",component:WaterMachineAppComponent,
    canActivate:[AuthGuardService,RoleGuardService],
    data:{expectedRole:['admin','user']}
  }
  ,
  {path:"newReport",component:CreateReportComponent,
  canActivate:[AuthGuardService,RoleGuardService],
  data:{expectedRole:['admin','user']}}

  ,
  {
    path:"resetPassword",component:ResetPasswordComponent
  }
  ,
  {
    path:"resetPassEmail",component:RequestChangePassComponent
  }

]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
