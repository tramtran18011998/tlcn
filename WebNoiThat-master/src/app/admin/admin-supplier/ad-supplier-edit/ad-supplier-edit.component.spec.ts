import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdSupplierEditComponent } from './ad-supplier-edit.component';

describe('AdSupplierEditComponent', () => {
  let component: AdSupplierEditComponent;
  let fixture: ComponentFixture<AdSupplierEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdSupplierEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdSupplierEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
