export interface Room {
    id: number;
    hotel: {id: number};
    roomType: {name: string};
    roomNumber: string;
    status: 'AVAILABLE' | 'MAINTENANCE' | 'BOOKED';
}