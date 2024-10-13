import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResetPassword } from 'src/app/Model/resetPassword';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent {
  forgotPasswordForm!: FormGroup;
  showAlert = false;
  alert = { type: 'success', message: '' };

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.forgotPasswordForm = this.fb.group(
      {
        email: ['', [Validators.required, Validators.email]],
        newPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
            Validators.pattern(
              /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
            ),
          ],
        ],
        confirmPassword: ['', Validators.required],
      },
      { validators: this.passwordsMatch }
    );
  }

  passwordsMatch(form: FormGroup): { passwordMismatch: boolean } | null {
    const newPassword = form.get('newPassword')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    return newPassword === confirmPassword ? null : { passwordMismatch: true };
  }

  onSubmit(): void {
    if (this.forgotPasswordForm.valid) {
      const resetPassword: ResetPassword = {
        email: this.forgotPasswordForm.get('email')?.value,
        newPassword: this.forgotPasswordForm.get('newPassword')?.value,
      };
      this.authService.resetPassword(resetPassword).subscribe({
        next: (response) => {
          this.alert = {
            type: 'success',
            message: response.message,
          };
          this.triggerAlert();
          setTimeout(() => {
            this.router.navigate(['/auth']);
          }, 2500);
        },
        error: (error) => {
          this.alert = {
            type: 'danger',
            message: error.error.message,
          };
          this.triggerAlert();
        },
      });
    }
  }

  goBack(): void {
    this.location.back();
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
