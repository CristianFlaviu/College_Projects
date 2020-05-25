import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnouncemnetsComponent } from './announcemnets.component';

describe('AnnouncemnetsComponent', () => {
  let component: AnnouncemnetsComponent;
  let fixture: ComponentFixture<AnnouncemnetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnouncemnetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnouncemnetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
