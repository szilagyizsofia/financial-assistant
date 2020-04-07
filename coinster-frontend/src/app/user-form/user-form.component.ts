import { Component, OnInit, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { User } from '../user';
import { RegistrationService } from '../registration.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
})
export class UserFormComponent implements OnInit, OnChanges {

  @Input() user: User;
  public newUser: User = new User();
  public model: User = new User();

  constructor(
    private registrationService: RegistrationService,
    private router: Router
  ) { }

  ngOnInit() { }

  ngOnChanges() {
    this.model = Object.assign({}, this.user);
  }

  submit(form) {
    this.newUser.username = this.model.username;
    this.newUser.password = this.model.password;
    this.newUser.plan = this.model.plan;
    this.newUser.currency = this.model.currency
    this.registrationService.createUser(this.newUser);
    this.newUser = null;
    this.router.navigate(['/login'])
  }
}
