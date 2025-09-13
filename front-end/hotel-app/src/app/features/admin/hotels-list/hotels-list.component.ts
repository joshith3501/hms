import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { CatalogService } from '../../../services/catalog.service'; 
import { Hotel } from '../../../models/hotel';
 
@Component({
  selector: 'app-hotels-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './hotels-list.component.html' ,
  styleUrl: './hotels-list.component.css' 
})
export class HotelsListComponent implements OnInit {
  private api = inject(CatalogService);
  hotels: Hotel[] = [];
  loading = true;
  error = '';
 
  ngOnInit() {
    this.api.hotels().subscribe({
      next: data => { this.hotels = data; this.loading = false; },
      error: err => { this.error = 'No Hotels Found'; this.loading = false; console.error(err); }
    });

    
  }

  delete(id: number | any){
      if(confirm("Are you sure you want to delete this hotel?")){
        this.api.deleteHotel(id).subscribe({
          next: () => this.hotels = this.hotels.filter(h => h.id !== id),
          error: (err) => alert('Error delete hotel: ' + err.message)
        });
      }
    }
}
 