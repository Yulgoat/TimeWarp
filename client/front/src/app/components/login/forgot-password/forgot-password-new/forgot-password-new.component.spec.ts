import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgotPasswordNewComponent } from './forgot-password-new.component';

describe('ForgotPasswordNewComponent', () => {
  let component: ForgotPasswordNewComponent;
  let fixture: ComponentFixture<ForgotPasswordNewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ForgotPasswordNewComponent]
    });
    fixture = TestBed.createComponent(ForgotPasswordNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
