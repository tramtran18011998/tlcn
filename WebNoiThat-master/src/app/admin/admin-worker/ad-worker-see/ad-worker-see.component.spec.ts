import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdWorkerSeeComponent } from './ad-worker-see.component';

describe('AdWorkerSeeComponent', () => {
  let component: AdWorkerSeeComponent;
  let fixture: ComponentFixture<AdWorkerSeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdWorkerSeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdWorkerSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
