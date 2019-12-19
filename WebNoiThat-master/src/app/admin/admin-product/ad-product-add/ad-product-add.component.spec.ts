import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdProductAddComponent } from './ad-product-add.component';

describe('AdProductAddComponent', () => {
  let component: AdProductAddComponent;
  let fixture: ComponentFixture<AdProductAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdProductAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdProductAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
