import { TestBed } from '@angular/core/testing';

import { RegularTransactionService } from './regular-transaction.service';

describe('RegularTransactionService', () => {
  let service: RegularTransactionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegularTransactionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
