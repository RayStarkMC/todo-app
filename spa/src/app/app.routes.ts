import { Routes } from '@angular/router';
import {ViewAllTodosPageComponent} from './pages/todo/view-all-page/view-all-todos-page.component';
import {CreateTodoPageComponent} from './pages/todo/create-todo-page/create-todo-page.component';
import {EditTodoPageComponent} from './pages/todo/edit-todo-page/edit-todo-page.component';

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
  {
    title: "ToDo編集",
    path: "todo/edit/:id",
    component: EditTodoPageComponent,
  },
  { // fallback
    path: "**",
    redirectTo: "/todo",
    pathMatch: "full",
  }
];
