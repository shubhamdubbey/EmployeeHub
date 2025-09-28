import { TestBed } from '@angular/core/testing';

import { AuthenticateUsersService } from './authenticate-users.service';

describe('AuthenticateUsersService', () => {
  let service: AuthenticateUsersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticateUsersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
