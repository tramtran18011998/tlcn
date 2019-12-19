import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdProductSeeComponent } from './ad-product-see.component';

describe('AdProductSeeComponent', () => {
  let component: AdProductSeeComponent;
  let fixture: ComponentFixture<AdProductSeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdProductSeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdProductSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
