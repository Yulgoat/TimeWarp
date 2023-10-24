import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CApopUpErrorsComponent } from './capop-up-errors.component';

describe('CApopUpErrorsComponent', () => {
  let component: CApopUpErrorsComponent;
  let fixture: ComponentFixture<CApopUpErrorsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CApopUpErrorsComponent]
    });
    fixture = TestBed.createComponent(CApopUpErrorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
