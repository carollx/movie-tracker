import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieFormComponent } from './components/movie-form/movie-form.component';
import { MovieRatingComponent } from './components/movie-rating/movie-rating.component';

const routes: Routes = [
  {
    path: '',
    component: MovieListComponent
  },
  {
    path: 'adicionar',
    component: MovieFormComponent
  },
  {
    path: 'avaliar/:id',
    component: MovieRatingComponent
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
