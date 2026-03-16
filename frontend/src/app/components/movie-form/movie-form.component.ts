import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.css']
})
export class MovieFormComponent implements OnInit {
  movieForm!: FormGroup;
  isSubmitting = false;
  error: string | null = null;
  success = false;

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.movieForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3)]],
      genero: ['', Validators.required],
      ano: ['', [Validators.required, Validators.pattern(/^\d{4}$/)]],
      descricao: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  onSubmit(): void {
    if (this.movieForm.invalid) {
      return;
    }

    this.isSubmitting = true;
    this.error = null;
    this.success = false;

    const newMovie: Movie = {
      ...this.movieForm.value,
      assistido: false
    };

    this.movieService.createMovie(newMovie).subscribe(
      (movie: Movie) => {
        this.success = true;
        this.isSubmitting = false;
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 1500);
      },
      (error) => {
        this.error = 'Erro ao cadastrar filme. Tente novamente.';
        this.isSubmitting = false;
        console.error('Erro:', error);
      }
    );
  }

  onCancel(): void {
    this.router.navigate(['/']);
  }

  get titulo() {
    return this.movieForm.get('titulo');
  }

  get genero() {
    return this.movieForm.get('genero');
  }

  get ano() {
    return this.movieForm.get('ano');
  }

  get descricao() {
    return this.movieForm.get('descricao');
  }
}
