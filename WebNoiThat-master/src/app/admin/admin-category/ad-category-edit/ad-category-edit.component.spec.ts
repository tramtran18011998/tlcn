import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdCategoryEditComponent } from './ad-category-edit.component';

describe('AdCategoryEditComponent', () => {
  let component: AdCategoryEditComponent;
  let fixture: ComponentFixture<AdCategoryEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdCategoryEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdCategoryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
