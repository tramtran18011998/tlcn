import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdProductEditComponent } from './ad-product-edit.component';

describe('AdProductEditComponent', () => {
  let component: AdProductEditComponent;
  let fixture: ComponentFixture<AdProductEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdProductEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdProductEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
