import { Routes } from '@angular/router';
import { HotelsListComponent } from './features/admin/hotels-list/hotels-list.component';
import { HotelDetailComponent } from './features/admin/hotel-detail/hotel-detail.component';
import { AddHotelComponent } from './features/admin/add-hotel/add-hotel.component';
import { HotelEditComponent } from './features/admin/hotel-edit/hotel-edit.component';
import { RoomTypeListComponent } from './features/admin/roomtype-list/roomtype-list.component';
import { AddRoomTypeComponent } from './features/admin/roomtype-add/roomtype-add.component';

export const routes: Routes = [
    {
        path: 'hotels', component: HotelsListComponent
    },
    {
        path: 'hotels/add', component: AddHotelComponent
    },
    {
        path: 'hotels/:id', component: HotelDetailComponent
    },
    {
        path: 'hotels/edit/:id', component: HotelEditComponent
    },
    {
        path: 'hotels/:hotelId/roomtypes', component: RoomTypeListComponent
    },
    {
        path: 'hotels/:hotelId/roomtypes/add', component: AddRoomTypeComponent
    },
    {
        path: '', redirectTo: 'hotels', pathMatch: 'full'
    },
    {
        path: '**', redirectTo: 'hotels'
    }
];
