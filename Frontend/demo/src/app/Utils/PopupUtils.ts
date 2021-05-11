import { Router } from "@angular/router";
import Swal from "sweetalert2";
import { NavigationUtils } from "./NavigationUtils";

export class PopupUtils {

    /**
     * Alert pop-up with fail status
     * 
     * @param  {string} message
     */
    public static alertWithFail(message: string) {
        Swal.fire('Failed', message, 'error');
    }

    /**
     * Alert pop-up with success status
     * 
     * @param  {string} message
     * @returns Promise
     */
    public static alertWithSuccess(message: string): Promise<any> {
        return Swal.fire('Success', message, 'success');
    }
    
    /**
     * Alert pop-up with success status and back to login page
     * 
     * @param  {string} message
     * @param  {Router} router
     */
    public static alertSuccessAndBackToLogin(message: string, router: Router) {
        this.alertWithSuccess(message).then(okay => {
            if (okay) {
                NavigationUtils.navigateToLogin(router);
            }
        });
    }
}