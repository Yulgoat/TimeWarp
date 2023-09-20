import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConvPopupComponent } from './new-conv-popup.component';

describe('NewConvPopupComponent', () => {
  let component: NewConvPopupComponent;
  let fixture: ComponentFixture<NewConvPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewConvPopupComponent]
    });
    fixture = TestBed.createComponent(NewConvPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
