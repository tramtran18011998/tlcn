import { User } from '../models/user';

export class AuthResponse {
    accessToken: string;
    tokenType: string = "Bearer";
    user:User;

    // constructor(accessToken: string, tokenType: string, user: User) {
    //     this.accessToken = accessToken;
    //     this.tokenType = tokenType;
    //     this.user=user;
    // }
    //constructor(){}
    
}