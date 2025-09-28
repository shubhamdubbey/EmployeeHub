import { Component } from '@angular/core';
import { Users } from '../../../models/users';
import { Grades } from '../../../models/grade';
import { HumanResourceService } from '../../human-resource.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-employees',
  templateUrl: './add-employees.component.html',
  styleUrl: './add-employees.component.css'
})
export class AddEmployeesComponent {

  user ! : Users;
  grade ! : Grades[];
  users : Users = new Users();
  errorMessage = '';
  check = false;  
  ifError = false;
  submitted = false;

  addEmployeeForm = new FormGroup(
    {
      employeeId: new FormControl('',[Validators.required, Validators.pattern('^[0-9]{6}$'), Validators.minLength(6), Validators.maxLength(6)]),
      firstName : new FormControl('',[Validators.pattern('^[A-Za-z]{1,10}$'), Validators.required]),
      lastName : new FormControl('',[Validators.pattern('^[A-Za-z]{1,10}$'), Validators.required, Validators.maxLength(15)]),
      emailId : new FormControl('',[Validators.pattern('^[a-zA-Z0-9.]+@cognizant\.com$'), Validators.required, Validators.maxLength(30)]),
      phoneNumber : new FormControl('',[Validators.pattern('^[0-9]{10}$'),  Validators.required]),
      role : new FormControl('',[Validators.required]),
      gradeId : new FormControl('',[Validators.required])
    }
  )

  constructor(private humanResourceService : HumanResourceService) {
    this.humanResourceService.getGrades()
    .subscribe((data) => {
      this.grade = data;
    })
  }

  ngOnInit(){
  }

  save(){
    this.humanResourceService.registerEmployee(this.users)
    .subscribe(data => {      
      this.submitted = true;
      this.check = true;
    },
    (error) => {
      this.check = true;
      this.errorMessage = error;
      this.ifError = true;
      this.submitted = true;
    });
  }

  reset(){
    this.check = false;
    this.ifError = false;
    this.submitted = false;
  }

  onSubmit(){
    this.users.employeeId = +this.addEmployeeForm.get('employeeId').value;
    this.users.firstName = this.addEmployeeForm.get('firstName').value;
    this.users.lastName = this.addEmployeeForm.get('lastName').value;
    this.users.emailAddress = this.addEmployeeForm.get('emailId').value;
    this.users.phoneNumber = this.addEmployeeForm.get('phoneNumber').value;
    this.users.role = this.addEmployeeForm.get('role').value;
    this.users.grade_id = +this.addEmployeeForm.get('gradeId').value;
    this.save();
  }
}
