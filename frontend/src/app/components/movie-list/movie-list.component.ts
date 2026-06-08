import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie';
import { Rating } from '../../models/rating';

interface MovieWithRating extends Movie {
  rating?: Rating;
}

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {
  movies: MovieWithRating[] = [];
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
    forkJoin({
      movies: this.movieService.getMovies(),
      ratings: this.movieService.getRatings()
    }).subscribe(
      ({ movies, ratings }) => {
        const ratingsByMovieId = new Map<number, Rating>();

        ratings.forEach((rating) => {
          if (!ratingsByMovieId.has(rating.filmeId)) {
            ratingsByMovieId.set(rating.filmeId, rating);
          }
        });

        this.movies = movies.map((movie) => ({
          ...movie,
          rating: movie.id ? ratingsByMovieId.get(movie.id) : undefined
        }));
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

  watchMovie(movie: MovieWithRating): void {
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

  rateMovie(movie: MovieWithRating): void {
    this.router.navigate(['/avaliar', movie.id]);
  }
}
