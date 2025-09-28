import { Component } from '@angular/core';
import { Users } from '../../../models/users';
import { HumanResourceService } from '../../human-resource.service';

@Component({
  selector: 'app-retrieve-employees',
  templateUrl: './retrieve-employees.component.html',
  styleUrl: './retrieve-employees.component.css'
})
export class RetrieveEmployeesComponent {
  user ! : Users[];
  empId : number;
  pageSize : number = 5;
  currentPage : number = 1;

  constructor(private humanResourceService : HumanResourceService) {
    this.humanResourceService.getEmployees()
    .subscribe( data => {
      this.user = data;

    })

  }  
  ngOnInit(): void{
    
  }
  
}
