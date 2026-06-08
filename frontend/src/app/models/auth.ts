export interface AuthRequest {
  email: string;
  password: string;
}

export interface RegisterRequest extends AuthRequest {
  nome: string;
}

export interface AuthResponse {
  token: string;
  userId: number;
  email: string;
  nome: string;
}
