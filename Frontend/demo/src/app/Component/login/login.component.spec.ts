import { ComponentFixture, TestBed } from '@angular/core/testing';

import * as loginComponent from './login.component';

describe('LoginComponent', () => {
  let component: loginComponent.LoginComponent;
  let fixture: ComponentFixture<loginComponent.LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ loginComponent.LoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(loginComponent.LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
