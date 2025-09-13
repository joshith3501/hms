export interface Hotel {
    id?: number;
    name: string;
    city: string;
    address?: string;
    status: 'ACTIVE' | 'INACTIVE';
}