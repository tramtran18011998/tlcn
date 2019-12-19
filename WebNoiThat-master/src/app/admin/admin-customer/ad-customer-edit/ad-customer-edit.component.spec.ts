import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdCustomerEditComponent } from './ad-customer-edit.component';

describe('AdCustomerEditComponent', () => {
  let component: AdCustomerEditComponent;
  let fixture: ComponentFixture<AdCustomerEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdCustomerEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdCustomerEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
