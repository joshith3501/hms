import { Injectable, inject } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";
import { Hotel } from "../models/hotel";
import { RoomType } from "../models/room-type";
import { Room } from "../models/room";

@Injectable({providedIn: 'root'})
export class CatalogService {
    private http = inject(HttpClient);
    private base = environment.apiBaseUrl;

    hotels(): Observable<Hotel[]>{
        return this.http.get<Hotel[]>(`${this.base}/api/admin/hotels`);
    }

    hotelById(id: number): Observable<Hotel>{
        return this.http.get<Hotel>(`${this.base}/api/admin/hotels/${id}`);
    }

    roomTypes(hotelId: number): Observable<RoomType[]> {
        return this.http.get<RoomType[]>(`${this.base}/api/admin/hotels/${hotelId}/roomtypes`);
    }

    rooms(hotelId: number, params?: {status?:string; roomTypeId?: string;}): Observable<Room[]> {
        const q = new URLSearchParams();
        if(params?.status) q.set('status', params.status);
        if(params?.roomTypeId) q.set('roomTypeId', String(params.roomTypeId));
        const qs = q.toString() ? `?${q}` : '';
        return this.http.get<Room[]>(`${this.base}/api/admin/hotels/${hotelId}/rooms${qs}`);

    }

    createHotel(hotel: Partial<Hotel>){
        return this.http.post<Hotel>(`${this.base}/api/admin/hotels`, hotel);
    }

    updateHotel(id: number, hotel: any){
        return this.http.put<Hotel>(`${this.base}/api/admin/hotels/${id}`, hotel);
    }

    deleteHotel(id: number) {
        return this.http.delete(`${this.base}/api/admin/hotels/${id}`);
    }
    
    // Room Types
    getRoomTypes(hotelId: number) { return this.http.get<RoomType[]>(`${this.base}/api/admin/hotels/${hotelId}/roomtypes`); }
    createRoomType(hotelId: number, rt: Partial<RoomType>) { return this.http.post<RoomType>(`${this.base}/api/admin/hotels/${hotelId}/roomtypes`, rt); }
    updateRoomType(hotelId:number, id: number, rt: RoomType) { return this.http.put<RoomType>(`${this.base}/api/admin/hotels/${hotelId}/roomtypes/${id}`, rt); }
    deleteRoomType(hotelId:number, id: number) { return this.http.delete(`${this.base}/api/admin/hotels/${hotelId}/roomtypes/${id}`); }
 
    // Rooms
    createRoom(hotelId: number, room: any) { return this.http.post(`${this.base}/api/admin/hotels/${hotelId}/rooms`, room); }
    updateRoom(hotelId: number, id: number, room: any) { return this.http.put(`${this.base}/api/admin/hotels/${hotelId}/rooms/${id}`, room); }
    deleteRoom(hotelId: number, id: number) { return this.http.delete(`${this.base}/api/admin/hotels/${hotelId}/rooms/${id}`); }
}