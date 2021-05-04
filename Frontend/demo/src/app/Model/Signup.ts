export class Signup {
    userName: string;
    password: string;
    confirmPassword: string;
    email: string;

    constructor(userName: string, password: string, confirmPassword: string, email: string) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }
}