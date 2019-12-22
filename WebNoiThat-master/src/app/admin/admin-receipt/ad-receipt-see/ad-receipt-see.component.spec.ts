import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdReceiptSeeComponent } from './ad-receipt-see.component';

describe('AdReceiptSeeComponent', () => {
  let component: AdReceiptSeeComponent;
  let fixture: ComponentFixture<AdReceiptSeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdReceiptSeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdReceiptSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
