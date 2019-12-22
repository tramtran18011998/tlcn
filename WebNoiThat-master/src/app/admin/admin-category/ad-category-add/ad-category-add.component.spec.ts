import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdCategoryAddComponent } from './ad-category-add.component';

describe('AdCategoryAddComponent', () => {
  let component: AdCategoryAddComponent;
  let fixture: ComponentFixture<AdCategoryAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdCategoryAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdCategoryAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
