import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CatalogService } from '../../../services/catalog.service';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
 
@Component({
  selector: 'app-add-room-type',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './roomtype-add.component.html',
  styleUrls: ['./roomtype-add.component.css']
})
export class AddRoomTypeComponent {
  hotelId!: number;
  formError = '';
 
  roomType = {
    name: '',
    maxGuests: 1,
    basePrice: 0
  };
 
  constructor(
    private api: CatalogService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
 
  ngOnInit() {
    this.hotelId = Number(this.route.snapshot.paramMap.get('hotelId'));
  }
 
  save() {
    this.api.createRoomType(this.hotelId, this.roomType).subscribe({
      next: () => this.router.navigate(['/hotels', this.hotelId]),
      error: (err: HttpErrorResponse) => { 
        const serverMsg = typeof err.error === 'string' ? err.error: '';
        if(err.status === 409 && serverMsg)
          {
            this.formError = serverMsg;
          } 
          else {
            this.formError = 'Could not save roomtype. Please try again';
          }
      }
    });
  }
 
  cancel() {
    this.router.navigate(['/hotels', this.hotelId]);
  }
}
 