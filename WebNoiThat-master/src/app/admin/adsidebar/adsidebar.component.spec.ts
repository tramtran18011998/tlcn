import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdsidebarComponent } from './adsidebar.component';

describe('AdsidebarComponent', () => {
  let component: AdsidebarComponent;
  let fixture: ComponentFixture<AdsidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdsidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdsidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
