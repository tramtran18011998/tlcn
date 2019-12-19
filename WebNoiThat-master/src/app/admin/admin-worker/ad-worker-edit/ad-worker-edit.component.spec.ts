import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdWorkerEditComponent } from './ad-worker-edit.component';

describe('AdWorkerEditComponent', () => {
  let component: AdWorkerEditComponent;
  let fixture: ComponentFixture<AdWorkerEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdWorkerEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdWorkerEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
