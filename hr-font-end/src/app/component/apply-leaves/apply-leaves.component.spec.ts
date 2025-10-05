import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyLeavesComponent } from './apply-leaves.component';

describe('ApplyLeavesComponent', () => {
  let component: ApplyLeavesComponent;
  let fixture: ComponentFixture<ApplyLeavesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApplyLeavesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyLeavesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
