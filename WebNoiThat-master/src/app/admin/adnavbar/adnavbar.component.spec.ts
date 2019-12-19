import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdnavbarComponent } from './adnavbar.component';

describe('AdnavbarComponent', () => {
  let component: AdnavbarComponent;
  let fixture: ComponentFixture<AdnavbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdnavbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdnavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
