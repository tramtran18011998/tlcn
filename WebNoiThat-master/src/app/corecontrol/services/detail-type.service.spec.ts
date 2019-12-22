import { TestBed } from '@angular/core/testing';

import { DetailTypeService } from './detail-type.service';

describe('DetailTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DetailTypeService = TestBed.get(DetailTypeService);
    expect(service).toBeTruthy();
  });
});
