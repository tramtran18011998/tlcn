import { User } from '../models/user';

export class ApiResponse {
    success: boolean;
    message: string ;

    constructor(success: boolean, message: string){
        this.success=success;
        this.message=message;
    }
    
}