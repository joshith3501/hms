import { Component,inject,OnInit } from '@angular/core';
import { CatalogService } from '../../../services/catalog.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-staff',
  imports: [],
  templateUrl: './add-staff.component.html',
  styleUrl: './add-staff.component.css'
})
export class AddStaffComponent {
  private api = inject(CatalogService);
  private router = inject(Router);
  formError = '';

  
}
