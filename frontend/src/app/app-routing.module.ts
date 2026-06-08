import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieFormComponent } from './components/movie-form/movie-form.component';
import { MovieRatingComponent } from './components/movie-rating/movie-rating.component';
import { AuthComponent } from './components/auth/auth.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  {
    path: 'login',
    component: AuthComponent
  },
  {
    path: '',
    component: MovieListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'adicionar',
    component: MovieFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'avaliar/:id',
    component: MovieRatingComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
