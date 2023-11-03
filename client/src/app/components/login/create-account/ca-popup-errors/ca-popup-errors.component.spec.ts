import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaPopupErrorsComponent } from './ca-popup-errors.component';

describe('CaPopupErrorsComponent', () => {
  let component: CaPopupErrorsComponent;
  let fixture: ComponentFixture<CaPopupErrorsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CaPopupErrorsComponent]
    });
    fixture = TestBed.createComponent(CaPopupErrorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
