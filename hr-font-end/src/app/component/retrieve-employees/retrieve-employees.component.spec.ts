import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrieveEmployeesComponent } from './retrieve-employees.component';

describe('RetrieveEmployeesComponent', () => {
  let component: RetrieveEmployeesComponent;
  let fixture: ComponentFixture<RetrieveEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RetrieveEmployeesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RetrieveEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
