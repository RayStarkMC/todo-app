import { Routes } from '@angular/router';
import {ViewAllTodosPageComponent} from './pages/todo/view-all-page/view-all-todos-page.component';

export const routes: Routes = [
  {
    title: "ToDo",
    path: "todo",
    component: ViewAllTodosPageComponent
  },
  { // fallback
    path: "**",
    redirectTo: "/todo",
    pathMatch: "full"
  }
];
