import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdWorkerAddComponent } from './ad-worker-add.component';

describe('AdWorkerAddComponent', () => {
  let component: AdWorkerAddComponent;
  let fixture: ComponentFixture<AdWorkerAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdWorkerAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdWorkerAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
