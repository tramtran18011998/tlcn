import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdCustomerSeeComponent } from './ad-customer-see.component';

describe('AdCustomerSeeComponent', () => {
  let component: AdCustomerSeeComponent;
  let fixture: ComponentFixture<AdCustomerSeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdCustomerSeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdCustomerSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
