import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WaterMachineAppComponent } from './water-machine-app.component';

describe('WaterMachineAppComponent', () => {
  let component: WaterMachineAppComponent;
  let fixture: ComponentFixture<WaterMachineAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WaterMachineAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WaterMachineAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
