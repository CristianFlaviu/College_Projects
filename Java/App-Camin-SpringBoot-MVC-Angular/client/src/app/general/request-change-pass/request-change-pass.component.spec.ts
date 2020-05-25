import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestChangePassComponent } from './request-change-pass.component';

describe('RequestChangePassComponent', () => {
  let component: RequestChangePassComponent;
  let fixture: ComponentFixture<RequestChangePassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestChangePassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestChangePassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
