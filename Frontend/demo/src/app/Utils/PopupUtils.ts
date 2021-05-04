import { Router } from "@angular/router";
import Swal from "sweetalert2";
import { NavigationUtils } from "./NavigationUtils";

export class PopupUtils {

    public static alertWithFail(message: string) {
        Swal.fire('Failed', message, 'error');
    }

    public static alertWithSuccess(message: string): Promise<any> {
        return Swal.fire('Success', message, 'success');
    }

    public static alertSuccessAndBackToLogin(message: string, router: Router) {
        this.alertWithSuccess(message).then(okay => {
            if (okay) {
                NavigationUtils.navigateToLogin(router);
            }
        });
    }
}