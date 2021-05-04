import { MessageResponse } from "./MessageResponse";

export class LoginRes extends MessageResponse {
    token!: string;

    constructor() {
        super();
    }
}