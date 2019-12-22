import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminReceiptComponent } from './admin-receipt.component';

describe('AdminReceiptComponent', () => {
  let component: AdminReceiptComponent;
  let fixture: ComponentFixture<AdminReceiptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminReceiptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminReceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
