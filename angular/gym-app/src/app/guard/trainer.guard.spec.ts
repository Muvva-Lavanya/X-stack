import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';
import {trainerGuard} from "./trainee.guard";



describe('trainerGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => trainerGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
