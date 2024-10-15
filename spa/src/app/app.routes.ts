import { Routes } from '@angular/router';
import {ViewAllTodosPageComponent} from './pages/todo/view-all-page/view-all-todos-page.component';
import {CreateTodoPageComponent} from './pages/todo/create-todo-page/create-todo-page.component';

export const routes: Routes = [
  {
    title: "ToDo",
    path: "todo",
    component: ViewAllTodosPageComponent,
  },
  {
    title: "ToDo作成",
    path: "todo/create",
    component: CreateTodoPageComponent,
  },
  { // fallback
    path: "**",
    redirectTo: "/todo",
    pathMatch: "full",
  }
];
