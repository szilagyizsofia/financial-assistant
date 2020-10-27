import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegularTransactionComponent } from './regular-transaction.component';

describe('RegularTransactionComponent', () => {
  let component: RegularTransactionComponent;
  let fixture: ComponentFixture<RegularTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegularTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegularTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
