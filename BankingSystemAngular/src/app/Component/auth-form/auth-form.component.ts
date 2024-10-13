import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.css'],
})
export class AuthFormComponent {
  signInForm!: FormGroup;
  signUpForm!: FormGroup;
  isSignUpMode = false;

  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.signInForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: [
        '',
        [Validators.required, Validators.minLength(8), this.passwordValidator],
      ],
    });

    this.signUpForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [Validators.required, Validators.minLength(8), this.passwordValidator],
      ],
    });
  }

  onSignIn(): void {
    if (this.signInForm.valid) {
      const username: string = this.signInForm.value.username;
      const password: string = this.signInForm.value.password;
      this.authService.logIn(username, password).subscribe({
        next: (response) => {
          if (this.isLoggedIn()) {
            this.checkCustomerDetailPresent();
            this.signInForm.reset();
          }
        },
        error: (error) => {
          const errorMessage =
            error.error?.message || 'An error occurred. Please try again.';
          this.alert.type = 'danger';
          this.alert.message = errorMessage;
          this.triggerAlert();
          this.signInForm.reset();
        },
      });
    }
  }

  onSignUp(): void {
    if (this.signUpForm.valid) {
      const username: string = this.signUpForm.value.username;
      const email: string = this.signUpForm.value.email;
      const password: string = this.signUpForm.value.password;
      this.authService.signUp(username, email, password).subscribe({
        next: (response) => {
          // console.log(response);
          this.alert.type = 'success';
          this.alert.message = 'Registration Successfull. Login now..!';
          this.triggerAlert();
          this.signUpForm.reset();
        },
        error: (error) => {
          // console.log(error)
          this.alert.type = 'danger';
          this.alert.message = error.error.message;
          this.triggerAlert();
          this.signUpForm.reset();
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

  checkCustomerDetailPresent(): void {
    this.customerService.isCustomerPresent().subscribe({
      next: (response) => {
        if (response.message === 'Present') {
          this.router.navigate(['/home']);
        } else {
          this.router.navigate(['/registerCustomer']);
        }
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }

  toggleMode(): void {
    this.isSignUpMode = !this.isSignUpMode;
  }

  private passwordValidator(control: FormControl) {
    const value = control.value;
    const hasUpperCase = /[A-Z]/.test(value);
    const hasLowerCase = /[a-z]/.test(value);
    const hasNumber = /\d/.test(value);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(value);

    if (!hasUpperCase || !hasLowerCase || !hasNumber || !hasSpecialChar) {
      return { passwordStrength: true };
    }
    return null;
  }
}
