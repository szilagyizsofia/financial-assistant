import { Component, OnInit } from '@angular/core';
import { UserService} from '../user.service';
import { User } from '../user'

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {

  public users: User[] = [];

  constructor(private userService: UserService) { }

  async ngOnInit(): Promise<void> {
    this.users = await this.userService.findAll();
  }

  onDeleteClick(id: number) {
    this.userService.deleteUser(id)
    .then(async () => {
      this.users = await this.userService.findAll();
    })
  }

  onChangePlan(id: number) {
    this.userService.changePlan(id)
    .then(async () => {
      this.users = await this.userService.findAll();
    })
    console.log('changed');
  }

}
