import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login-signup',
  templateUrl: './login-signup.component.html',
  styleUrls: ['./login-signup.component.css'],
})
export class LoginSignupComponent {
  loginForm!: FormGroup;
  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
          ),
        ],
      ],
    });
  }

  onSubmit(): void {
    this.loginForm.markAllAsTouched();
    if (this.loginForm.valid) {
      const username: string = this.loginForm.value.username;
      const password: string = this.loginForm.value.password;
      this.authService.adminLogIn(username, password).subscribe({
        next: (response) => {
          if (this.isLoggedIn()) {
            this.router.navigate(['admin/adminHome']);
            this.loginForm.reset();
          }
        },
        error: (error) => {
          console.log(error);
          this.alert.type = 'danger';
          this.alert.message = error.error.message;
          this.triggerAlert();
          this.loginForm.reset();
        },
      });
    }
  }

  // onSignUp() {
  //   this.signUpForm.markAllAsTouched();
  //   if (this.signUpForm.valid) {
  //     const username: string = this.signUpForm.value.username;
  //     const email: string = this.signUpForm.value.email;
  //     const password: string = this.signUpForm.value.password;
  //     this.authService.adminSignUp(username, email, password).subscribe({
  //       next: (response) => {
  //         // console.log(response);
  //         this.alert.type = 'success';
  //         this.alert.message = 'Registration Successfull';
  //         this.triggerAlert();
  //       },
  //       error: (error) => {
  //         // console.log(error)
  //         this.alert.type = 'danger';
  //         this.alert.message = error.error.message;
  //         this.triggerAlert();
  //       },
  //     });
  //   }
  // }

  onSignIn() {
    this.loginForm.markAllAsTouched();
    if (this.loginForm.valid) {
      const username: string = this.loginForm.value.username;
      const password: string = this.loginForm.value.password;
      this.authService.adminLogIn(username, password).subscribe({
        next: (response) => {
          if (this.isLoggedIn()) {
            this.router.navigate(['admin/adminHome']);
            this.loginForm.reset();
          }
        },
        error: (error) => {
          this.alert.type = 'danger';
          this.alert.message = error.error.message;
          this.triggerAlert();
          this.loginForm.reset();
        },
      });
    }
  }

  isLoggedIn(): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate(['/auth']);
      return false;
    }
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
