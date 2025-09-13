import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomTypeListComponent } from './roomtype-list.component';

describe('RoomtypeListComponent', () => {
  let component: RoomTypeListComponent;
  let fixture: ComponentFixture<RoomTypeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomTypeListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
