import { Router } from "@angular/router";

export class NavigationUtils {

    /**
     * Navigate to login page
     * 
     * @param  {Router} router
     */
    public static navigateToLogin(router: Router) {
        router.navigate(["/login"]);
    }
    
    /**
     * Navigate to dynamic page from parameter
     * 
     * @param  {Router} router
     * @param  {string} page
     */
    public static navigateToPage(router: Router, page: string) {
        router.navigate([page]);
    }
}