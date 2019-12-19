import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdSupplierAddComponent } from './ad-supplier-add.component';

describe('AdSupplierAddComponent', () => {
  let component: AdSupplierAddComponent;
  let fixture: ComponentFixture<AdSupplierAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdSupplierAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdSupplierAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
