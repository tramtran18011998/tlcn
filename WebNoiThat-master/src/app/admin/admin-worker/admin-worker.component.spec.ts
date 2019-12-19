import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkerComponent } from './admin-worker.component';

describe('AdminWorkerComponent', () => {
  let component: AdminWorkerComponent;
  let fixture: ComponentFixture<AdminWorkerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminWorkerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
