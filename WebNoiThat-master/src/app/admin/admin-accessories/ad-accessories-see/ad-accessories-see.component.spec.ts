import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdAccessoriesSeeComponent } from './ad-accessories-see.component';

describe('AdAccessoriesSeeComponent', () => {
  let component: AdAccessoriesSeeComponent;
  let fixture: ComponentFixture<AdAccessoriesSeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdAccessoriesSeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdAccessoriesSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
