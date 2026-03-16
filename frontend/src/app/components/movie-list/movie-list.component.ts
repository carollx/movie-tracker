import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = [];
  isLoading = false;
  error: string | null = null;

  constructor(
    private movieService: MovieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.isLoading = true;
    this.error = null;
    this.movieService.getMovies().subscribe(
      (movies: Movie[]) => {
        this.movies = movies;
        this.isLoading = false;
      },
      (error) => {
        this.error = 'Erro ao carregar filmes. Tente novamente mais tarde.';
        this.isLoading = false;
        console.error('Erro ao carregar filmes:', error);
      }
    );
  }

  addMovie(): void {
    this.router.navigate(['/adicionar']);
  }

  watchMovie(movie: Movie): void {
    if (movie.id) {
      this.movieService.markAsWatched(movie.id).subscribe(
        () => {
          movie.assistido = true;
        },
        (error) => {
          this.error = 'Erro ao marcar como assistido.';
          console.error('Erro:', error);
        }
      );
    }
  }

  rateMovie(movie: Movie): void {
    this.router.navigate(['/avaliar', movie.id]);
  }
}
