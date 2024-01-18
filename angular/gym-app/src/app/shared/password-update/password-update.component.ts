import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SigninService} from "../../../service/signin.service";
import {SnackBarService} from "../../../service/snack-bar.service";
import {UpdatePassword} from "../../model/UpdatePassword";

@Component({
  selector: 'app-password-update',
  templateUrl: './password-update.component.html',
  styleUrls: ['./password-update.component.css']
})
export class PasswordUpdateComponent {


  userNameFromState: string | undefined;

  changePasswordForm: any;
  hideOldPassword = true;
  hideNewPassword: boolean = true;

  constructor(private router: Router, private signinService: SigninService, private snackBar: SnackBarService) {
    this.changePasswordForm = new FormGroup(
      {
        username: new FormControl('', Validators.required),
        oldPassword: new FormControl('', Validators.required),
        newPassword: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{8,}$/)]),
        confirmPassword: new FormControl('', Validators.required)
      }
    );
  }

  get username() {
    return this.changePasswordForm.get('username');
  }

  get oldPassword() {
    return this.changePasswordForm.get('oldPassword');
  }

  get newPassword() {
    return this.changePasswordForm.get('newPassword');
  }

  get confirmPassword() {
    return this.changePasswordForm.get('confirmPassword');
  }

  ngOnInit() {
    const state = window.history.state;
    this.userNameFromState = state.userName;
    this.changePasswordForm.get('username')?.setValue(this.userNameFromState);
  }

  onSubmit() {
    let updatePassword: UpdatePassword = new UpdatePassword();
    updatePassword.userName = this.userNameFromState;
    updatePassword.oldPassword = this.changePasswordForm.value.oldPassword;
    updatePassword.newPassword = this.changePasswordForm.value.newPassword;
    this.signinService.updateCredentials(updatePassword).subscribe({
      next:(data:any)=>{
        this.snackBar.openSnackBar("Password updated succesfully, Please log-in again", 1500);
        this.router.navigate(['signIn']);
      },error:(error:any)=>{
        this.snackBar.openSnackBar("Please check your password,Try again", 1500);
      }
    })


  }

  toggleOldPasswordVisibility($event: Event) {
    this.hideOldPassword = !this.hideOldPassword;
    $event.preventDefault();
  }

  toggleNewPasswordVisibility($event: Event) {
    this.hideNewPassword = !this.hideNewPassword;
    $event.preventDefault();
  }
}
