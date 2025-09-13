import { Component, OnInit, inject } from '@angular/core';
import { CatalogService } from '../../../services/catalog.service';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { RoomType } from '../../../models/room-type';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-roomtype-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './roomtype-list.component.html',
  styleUrl: './roomtype-list.component.css'
})
export class RoomTypeListComponent implements OnInit {
  private api = inject(CatalogService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
 
  hotelId!: number;
  roomTypes: RoomType[] = [];
 
  ngOnInit() {
    this.hotelId = Number(this.route.snapshot.paramMap.get('hotelId'));
    this.load();
  }
 
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