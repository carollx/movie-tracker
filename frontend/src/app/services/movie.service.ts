import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie';
import { Rating } from '../models/rating';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  // Obter todos os filmes
  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.apiUrl}/filmes`);
  }

  // Cadastrar novo filme
  createMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(`${this.apiUrl}/filmes`, movie);
  }

  // Marcar filme como assistido
  markAsWatched(movieId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/filmes/${movieId}/assistido`, {});
  }

  // Avaliar filme
  rateMovie(rating: Rating): Observable<Rating> {
    return this.http.post<Rating>(`${this.apiUrl}/avaliacoes`, rating);
  }
}
