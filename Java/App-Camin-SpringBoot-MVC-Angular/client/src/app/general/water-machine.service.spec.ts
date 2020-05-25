import { TestBed } from '@angular/core/testing';

import { WaterMachineService } from './water-machine.service';

describe('WaterMachineService', () => {
  let service: WaterMachineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WaterMachineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
