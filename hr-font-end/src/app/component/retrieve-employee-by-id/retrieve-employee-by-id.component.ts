import { Component } from '@angular/core';
import { Users } from '../../../models/users';
import { HumanResourceService } from '../../human-resource.service';
import {FormGroup, FormControl, Validators} from '@angular/forms'
import { Router } from '@angular/router';

@Component({
  selector: 'app-retrieve-employee-by-id',
  templateUrl: './retrieve-employee-by-id.component.html',
  styleUrl: './retrieve-employee-by-id.component.css'
})
export class RetrieveEmployeeByIdComponent {
  user ! : Users;
  users : Users = new Users();
  submitted = false;
  noRecordFound = false;
  empId : number;
  reset = false;
  idForm = new FormGroup({
    id : new FormControl('',[Validators.required, Validators.pattern('^[0-9]{6}$'), Validators.minLength(6), Validators.maxLength(6)])
  })

  constructor(private router : Router,private humanResourceService : HumanResourceService) {}

   deleteEmployee(id : number){
    this.humanResourceService.deleteEmployee(id)
      .subscribe(data => console.log(data) );
      this.router.navigateByUrl('/Refresh Component',{skipLocationChange: true}).then(() =>
    {
      this.router.navigate(['retrieveEmployeeById']);
    })
  }

    
  async search(){
    this.humanResourceService.getEmployeeById(this.users)
    .subscribe( data => {
      this.user = data;
      this.submitted = true;
      this.noRecordFound = false;
    },
    (error) => {
      this.noRecordFound = true;
      this.submitted = true;
      console.log("inside terror")
    });
  }

  ngOnInit(){

  }

  onReset(){
    this.reset = true;
  }

  onSubmit(){
    this.reset = false;
    this.users.employeeId = +this.idForm.controls['id'].value;
    this.search();
  }
}
