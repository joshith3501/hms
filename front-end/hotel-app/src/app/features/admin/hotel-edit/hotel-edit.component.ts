import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CatalogService } from '../../../services/catalog.service';
import { Hotel } from '../../../models/hotel';
 
@Component({
  selector: 'app-hotel-edit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './hotel-edit.component.html',
})
export class HotelEditComponent implements OnInit {
  private api = inject(CatalogService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
 
  hotelId!: number;
  hotel: Hotel = { id: 0, name: '', city: '', address: '', status: 'ACTIVE' };
 
  ngOnInit() {
    this.hotelId = Number(this.route.snapshot.paramMap.get('id'));
    this.api.hotelById(this.hotelId).subscribe({
      next: (h) => this.hotel = h,
      error: (err) => alert('Error loading hotel: ' + err.message)
    });
  }
 
  save() {
    this.api.updateHotel(this.hotelId, this.hotel).subscribe({
      next: () => this.router.navigate(['/hotels']),
      error: (err) => alert('Error updating hotel: ' + err.message)
    });
  }
}