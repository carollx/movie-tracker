import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Rating } from '../../models/rating';

@Component({
  selector: 'app-movie-rating',
  templateUrl: './movie-rating.component.html',
  styleUrls: ['./movie-rating.component.css']
})
export class MovieRatingComponent implements OnInit {
  ratingForm!: FormGroup;
  movieId: number | null = null;
  isSubmitting = false;
  error: string | null = null;
  success = false;

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.movieId = params['id'];
      this.initializeForm();
    });
  }

  initializeForm(): void {
    this.ratingForm = this.fb.group({
      nota: [3, [Validators.required, Validators.min(1), Validators.max(5)]],
      comentario: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  onSubmit(): void {
    if (this.ratingForm.invalid || !this.movieId) {
      return;
    }

    this.isSubmitting = true;
    this.error = null;
    this.success = false;

    const rating: Rating = {
      filmeId: this.movieId,
      nota: this.ratingForm.value.nota,
      comentario: this.ratingForm.value.comentario
    };

    this.movieService.rateMovie(rating).subscribe(
      (response: Rating) => {
        this.success = true;
        this.isSubmitting = false;
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 1500);
      },
      (error) => {
        this.error = 'Erro ao enviar avaliação. Tente novamente.';
        this.isSubmitting = false;
        console.error('Erro:', error);
      }
    );
  }

  onCancel(): void {
    this.router.navigate(['/']);
  }

  selectRating(rate: number): void {
    this.ratingForm.patchValue({ nota: rate });
  }

  getRatingArray(): number[] {
    return [1, 2, 3, 4, 5];
  }

  get nota() {
    return this.ratingForm.get('nota');
  }

  get comentario() {
    return this.ratingForm.get('comentario');
  }
}
