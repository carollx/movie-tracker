import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  authForm: FormGroup;
  isRegisterMode = false;
  isSubmitting = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.authForm = this.fb.group({
      nome: [''],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  toggleMode(): void {
    this.isRegisterMode = !this.isRegisterMode;
    this.error = null;

    const nomeControl = this.authForm.get('nome');
    if (this.isRegisterMode) {
      nomeControl?.setValidators([Validators.required, Validators.minLength(2)]);
    } else {
      nomeControl?.clearValidators();
      nomeControl?.setValue('');
    }
    nomeControl?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.authForm.invalid) {
      this.authForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    this.error = null;

    const request$ = this.isRegisterMode
      ? this.authService.register(this.authForm.value)
      : this.authService.login({
          email: this.authForm.value.email,
          password: this.authForm.value.password
        });

    request$.subscribe({
      next: () => {
        this.isSubmitting = false;
        this.router.navigate(['/']);
      },
      error: () => {
        this.error = this.isRegisterMode
          ? 'Não foi possível criar a conta.'
          : 'Email ou senha inválidos.';
        this.isSubmitting = false;
      }
    });
  }

  get nome() {
    return this.authForm.get('nome');
  }

  get email() {
    return this.authForm.get('email');
  }

  get password() {
    return this.authForm.get('password');
  }
}
