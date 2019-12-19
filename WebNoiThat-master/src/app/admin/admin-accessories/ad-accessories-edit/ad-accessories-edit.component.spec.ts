import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdAccessoriesEditComponent } from './ad-accessories-edit.component';

describe('AdAccessoriesEditComponent', () => {
  let component: AdAccessoriesEditComponent;
  let fixture: ComponentFixture<AdAccessoriesEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdAccessoriesEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdAccessoriesEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
