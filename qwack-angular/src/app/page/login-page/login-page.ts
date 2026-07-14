import { Component, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  protected formAuth!: FormGroup;
  protected formCtrlLogin!: FormControl;
  protected formCtrlPassword!: FormControl;
  protected loginError = signal(false);

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formCtrlLogin = this.formBuilder.control("", Validators.required);
    this.formCtrlPassword = this.formBuilder.control("", [Validators.required, Validators.minLength(4)]);

    this.formAuth = this.formBuilder.group({
      login: this.formCtrlLogin,
      password: this.formCtrlPassword
    });
  }

  public auth() {
    this.authService.auth(this.formAuth.getRawValue()).subscribe({
      next: () => this.router.navigate(['accueil']),
      error: () => this.loginError.set(true)
    });
  }
}
