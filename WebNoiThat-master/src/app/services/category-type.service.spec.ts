import { TestBed } from '@angular/core/testing';

import { CategoryTypeService } from './category-type.service';

describe('CategoryTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CategoryTypeService = TestBed.get(CategoryTypeService);
    expect(service).toBeTruthy();
  });
});
