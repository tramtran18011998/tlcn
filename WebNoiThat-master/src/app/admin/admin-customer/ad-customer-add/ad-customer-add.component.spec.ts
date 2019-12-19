import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdCustomerAddComponent } from './ad-customer-add.component';

describe('AdCustomerAddComponent', () => {
  let component: AdCustomerAddComponent;
  let fixture: ComponentFixture<AdCustomerAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdCustomerAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdCustomerAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
