import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateEmployeeGradeComponent } from './update-employee-grade.component';

describe('UpdateEmployeeGradeComponent', () => {
  let component: UpdateEmployeeGradeComponent;
  let fixture: ComponentFixture<UpdateEmployeeGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateEmployeeGradeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateEmployeeGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
