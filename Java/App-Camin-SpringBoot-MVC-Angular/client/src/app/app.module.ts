import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatStepperModule } from '@angular/material/stepper';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatRippleModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTreeModule } from '@angular/material/tree';
import { TableMaterialComponent } from './table-material/table-material.component';
import { RoomComponent } from './general/room/room.component';
import {HttpClientModule} from '@angular/common/http';
import { RegisterFormComponent } from './general/register-form/register-form.component'

import {FormsModule} from '@angular/forms';

import { AllUsersComponent } from './general/all-users/all-users.component';
import { HomeAdminComponent } from './general/home-admin/home-admin.component';

import { FormBuilder ,ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './general/login/login.component';
import { JwtHelperService, JWT_OPTIONS   } from '@auth0/angular-jwt';
import { AnnouncemnetsComponent } from './general/announcemnets/announcemnets.component';
import { AnnouncementDetailsComponent } from './general/announcement-details/announcement-details.component';
import { NavigationBarComponent } from './general/navigation-bar/navigation-bar.component';
import { LogoutComponent } from './general/logout/logout.component';
import { WaterMachineAppComponent } from './general/water-machine-app/water-machine-app.component';
import { ResetPasswordComponent } from './general/reset-password/reset-password.component';

import { RequestChangePassComponent } from './general/request-change-pass/request-change-pass.component';
import { CreateReportComponent } from './general/create-report/create-report.component';
import { ReportDetailsComponent } from './general/report-details/report-details.component';
import { RoomDetailsComponent } from './general/room-details/room-details.component';




@NgModule({
  declarations: [
    AppComponent,
    TableMaterialComponent,
    RoomComponent,
    RegisterFormComponent,
    AllUsersComponent,
    HomeAdminComponent,
    LoginComponent,
    AnnouncemnetsComponent,
    AnnouncementDetailsComponent,
    NavigationBarComponent,
    LogoutComponent,
    WaterMachineAppComponent,
    ResetPasswordComponent,
    RequestChangePassComponent,
    CreateReportComponent,
    ReportDetailsComponent,
    RoomDetailsComponent




  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    HttpClientModule,
   
 
   
  ],
  providers: [ { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService],
  bootstrap: [AppComponent]
})





export class AppModule { }
