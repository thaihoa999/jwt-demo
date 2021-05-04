export class UpdatePassword {
    token: string;
    newPassword: string;
    confirmPassword: string;

    constructor(token: string, newPassword: string, confirmPassword: string) {
        this.token = token;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
}