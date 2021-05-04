import { Router } from "@angular/router";

export class NavigationUtils {

    public static navigateToLogin(router: Router) {
        router.navigate(["/login"]);
    }
}