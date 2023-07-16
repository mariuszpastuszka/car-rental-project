import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './components/app-component/app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ClientsComponent} from './components/clients/clients.component';
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {HttpClientModule} from "@angular/common/http";
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { HomePageComponent } from './components/home-page/home-page.component';
import {RouterModule, RouterOutlet} from "@angular/router";
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {clientsPageUrl, homePageUrl, loginPageUrl, notFoundPageUrl} from "./models/links";
import {ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    TopBarComponent,
    NavigationBarComponent,
    HomePageComponent,
    NotFoundPageComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatButtonToggleModule,
    RouterOutlet,
    RouterModule.forRoot([
      {path: homePageUrl, component: HomePageComponent},
      {path: clientsPageUrl, component: ClientsComponent},
      {path: loginPageUrl, component: LoginComponent},
      {path: notFoundPageUrl, component: NotFoundPageComponent}
    ]),
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
