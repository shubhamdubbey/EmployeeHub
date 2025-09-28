import { Component } from '@angular/core';
import { HumanResourceService } from '../../human-resource.service';
import {FormGroup, FormControl, Validators} from '@angular/forms'
import { Users } from '../../../models/users';
import { Grades } from '../../../models/grade';
import { ActivatedRoute, Route } from '@angular/router';
@Component({
  selector: 'app-update-employee-grade',
  templateUrl: './update-employee-grade.component.html',
  styleUrl: './update-employee-grade.component.css'
})

export class UpdateEmployeeGradeComponent {
  user ! : Users;
  grade ! : Grades[];
  users : Users = new Users();
  newGradeId : number;
  submitted = false;
  noRecordFound = false;
  empId : number;
  errorMessage = '';
  ifError : boolean = false;
  check : boolean = false;
  updateForm : FormGroup;

  constructor(private route : ActivatedRoute, private humanResourceService : HumanResourceService) {
    this.humanResourceService.getGrades()
    .subscribe((data) => {
      this.grade = data;
    })
  }

 async update(){
     this.humanResourceService.updateEmployee(this.users, this.newGradeId)
    .subscribe( () => {this.check = true},(error)=>{
      this.errorMessage = error.toString();
      this.ifError = true;
      this.check = true;
    });
  }

  ngOnInit(){
    this.empId = +this.route.snapshot.paramMap.get('empId');
    this.updateForm = new FormGroup({
      id : new FormControl(this.empId),
      gradeId : new FormControl('',[Validators.required])
    })
  }

  onSubmit(){
    this.submitted = true;
    this.users.employeeId = +this.updateForm.get('id').value;
    this.newGradeId = +this.updateForm.get('gradeId').value;
    this.update();
    this.humanResourceService.getEmployeeById(this.users).subscribe((data) => {
      this.user = data;
    })

  }
}
