import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { CatalogService } from '../../../services/catalog.service';
import { Hotel } from '../../../models/hotel';
import { RoomType } from '../../../models/room-type';
import { Room } from '../../../models/room';
 
@Component({
  selector: 'app-hotel-detail',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './hotel-detail.component.html' ,
  styleUrl: './hotel-detail.component.css'
})
export class HotelDetailComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private api = inject(CatalogService);
 
  activeTab: 'roomTypes' | 'rooms' = 'roomTypes'

  hotel!: Hotel;
  hotelId!: number;
 
  
  roomTypes: RoomType[] = [];
  rooms: Room[] = [];
 
  status = '';
 
  typesLoading = true; typesError = '';
  roomsLoading = true; roomsError = '';
 
  ngOnInit() {
  const idParam = this.route.snapshot.paramMap.get('id');
  if (idParam) {
    this.hotelId = Number(idParam);
    console.log(this.hotelId);
 
    this.api.hotelById(this.hotelId).subscribe({
      next: h => this.hotel = h,
      error: e => console.error(e)
    });
 
    this.api.roomTypes(this.hotelId).subscribe({
      next: d => { this.roomTypes = d; this.typesLoading = false; },
      error: e => { this.typesError = 'Failed to load room types'; }
    });
 
    this.api.rooms(this.hotelId).subscribe({
      next: r => { this.rooms = r; this.roomsLoading = false; },
      error: e => { this.roomsError = 'Failed to load rooms'; }
    });
  } else {
    // If no id → we're in "add" mode, so skip fetching
    console.log("Add mode - no hotelId");
  }
   //For RoomType List
   this.hotelId = Number(this.route.snapshot.paramMap.get('id'));
   this.load();
}
 
  loadRooms() {
    this.roomsLoading = true; this.roomsError = '';
    this.api.rooms(this.hotelId, { status: this.status || undefined }).subscribe({
      next: d => { this.rooms = d; this.roomsLoading = false; },
      error: _ => { this.roomsError = 'Failed to load rooms'; this.roomsLoading = false; }
    });
  }
 
  // crude helper to show type name; in real app fetch by id or include name in room payload
  displayType(roomTypeId?: number) {
    const t = this.roomTypes.find(x => x.id === roomTypeId);
    return t?.name || t?.type || roomTypeId || '-';
  }

  deleteRoom(id: number) {
    if(confirm('Delete room?')) {
      this.api.deleteRoom(this.hotelId, id).subscribe({
        next: () => this.rooms = this.rooms.filter(r => r.id !== id),
        error: (err) => alert('Error deleting room: ' + err.message)
      })
    }
  }

  //Roomtype List
  load() {
    this.api.getRoomTypes(this.hotelId).subscribe({
      next: (d) => this.roomTypes = d,
      error: (err) => alert('Error loading room types: ' + err.message)
    });
  }
 
  delete(id: number) {
    if (confirm('Delete room type?')) {
      this.api.deleteRoomType(this.hotelId, id).subscribe({
        next: () => this.roomTypes = this.roomTypes.filter(r => r.id !== id),
        error: (err) => {
          if(err.status === 400) {
            alert('Cannot delete this Rom Type because rooms exist for it');
          }
          else{
             alert('Error deleting room type: ' + err.message)
          }
         }
      });
    }
  }
}
 