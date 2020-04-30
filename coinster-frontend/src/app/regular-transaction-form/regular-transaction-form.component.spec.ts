import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegularTransactionFormComponent } from './regular-transaction-form.component';

describe('RegularTransactionFormComponent', () => {
  let component: RegularTransactionFormComponent;
  let fixture: ComponentFixture<RegularTransactionFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegularTransactionFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegularTransactionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
