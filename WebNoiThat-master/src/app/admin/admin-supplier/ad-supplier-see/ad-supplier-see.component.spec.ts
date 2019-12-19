import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdSupplierSeeComponent } from './ad-supplier-see.component';

describe('AdSupplierSeeComponent', () => {
  let component: AdSupplierSeeComponent;
  let fixture: ComponentFixture<AdSupplierSeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdSupplierSeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdSupplierSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
