import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrieveEmployeeByIdComponent } from './retrieve-employee-by-id.component';

describe('RetrieveEmployeeByIdComponent', () => {
  let component: RetrieveEmployeeByIdComponent;
  let fixture: ComponentFixture<RetrieveEmployeeByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RetrieveEmployeeByIdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RetrieveEmployeeByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
