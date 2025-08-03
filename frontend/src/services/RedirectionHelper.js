import AppRoutes from "../utils/AppRoutes";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import UserContext from "../utils/UserContext";
import Navigate from "./NavigationService";

class RedirectionHelper {
    
    static fromLogin() {
        const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

        if(isLoggedIn) {
            Navigate.to({ path: AppRoutes.home, clearBrowserStack: true });
        }
    }

    static fromHome() {
        const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

        if( !isLoggedIn ) {
            Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
        } 
        else {
            const loggedInUser = UserContext.getLoggedInUser();
    
            if(loggedInUser.name == null) {
                Navigate.to({ path: AppRoutes.editProfile, clearBrowserStack: true });
            }
        }
    }

    static fromEditProfile() {
        const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

        if( !isLoggedIn ) {
            Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
        }
    }

}

// function RedirectionHelper() {
//     const path = window.location.pathname;
//     const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

//     if( !isLoggedIn ) {
//         if(path !== AppRoutes.login) {
//             Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
//         }
//     } 
//     else {
//         const loggedInUser = UserContext.getLoggedInUser();

//         if(loggedInUser.name == null) {
//             if(path !== AppRoutes.editProfile) {
//                 Navigate.to({ path: AppRoutes.editProfile, clearBrowserStack: true });
//             }
//         }
//         else
//         // else {
//         //     if(path !== AppRoutes.home) {
//         //         Navigate.to({ path: AppRoutes.home, clearBrowserStack: true });
//         //     }
//         // }
//     }
// }

export default RedirectionHelper;