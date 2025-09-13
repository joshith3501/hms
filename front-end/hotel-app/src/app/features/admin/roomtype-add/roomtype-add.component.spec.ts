import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomtypeAddComponent } from './roomtype-add.component';

describe('RoomtypeAddComponent', () => {
  let component: RoomtypeAddComponent;
  let fixture: ComponentFixture<RoomtypeAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomtypeAddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomtypeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
