import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdAccessoriesAddComponent } from './ad-accessories-add.component';

describe('AdAccessoriesAddComponent', () => {
  let component: AdAccessoriesAddComponent;
  let fixture: ComponentFixture<AdAccessoriesAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdAccessoriesAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdAccessoriesAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
