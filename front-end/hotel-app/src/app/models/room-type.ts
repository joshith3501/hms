export type RoomTypeName = 'Regal' | 'Statesman' | 'Artist' | string;

export interface RoomType {
    id: number;
    hotel: {id: number};
    name?: string;
    type?: string;
    maxGuests: number;
    basePrice: number;
    amenitiesJson?: string;
}