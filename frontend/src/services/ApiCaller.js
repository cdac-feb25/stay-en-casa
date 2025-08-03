import axios from "axios";
import Endpoints from "../utils/ApiEndpoints";
import PayloadHelper from "../utils/PayloadHelper";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import AxiosHelper from "./AxiosHelper";
import ApiActionHelper from "../utils/ApiActionHelper";
import Navigate from "./NavigationService";
import AppRoutes from "../utils/AppRoutes";

class ApiCaller {

    static async getProfile() {
        const url = Endpoints.profile;
        
        return AxiosHelper
            .GET({ url, isAuthHeader: true })
            .then((response) => {
                ApiActionHelper.profileResponseHelper(response);
                    
                return response;
            });
    }

    static async forgotPassword(resetEmail) {
        const url = Endpoints.forgotPassword;
        console.log("calling : " + url);
        console.log("payload ", resetEmail);

        return AxiosHelper.POST({ url, body: resetEmail, isAuthHeader: false, withCredentials: false });
    }

    static async changePassword(newPasswordData) {
        const url = Endpoints.changePassword;
        console.log("calling : " + url);
        console.log("payload ", newPasswordData);

        return AxiosHelper.PUT({ url, body: newPasswordData, isAuthHeader: false });
    }

    /**
     * 
     * @param {import("../utils/UserContext").User} profile 
     */
    static async updateProfile(profile) {
        const url = Endpoints.profile;
        console.log("calling : " + url);

        return AxiosHelper
            .PUT({ url, body: profile, isAuthHeader: true })
            .then((response) => {
                ApiActionHelper.profileResponseHelper(response);

                return response;
            });
    }

    static async login({email, password}) {
        if(!email || !password) {
            throw new Error("Invalid email / password while calling Axios Helper");
        }

        const url = Endpoints.login;
        const body = PayloadHelper.forLoginAndSignup({ email, password, deviceId: LocalStorageHelper.getDeviceId() });
        
        console.log("calling : " + url);
        console.log("with : " + body);

        return AxiosHelper
            .POST({ url, body, isAuthHeader: false, withCredentials: true })
            .then((response) => {
                ApiActionHelper.tokenResponseHelper(response);

                const { uid } = response.data;
                ApiActionHelper.loginSignupResponseHelper({ uid, email });

                return response;
            });
    }

    /**
     * Never call this method from 
     * 'ApiActionHelper.Error'
     * @returns void
     */
    static async logout() {
        const logoutURL = Endpoints.logout;

        await AxiosHelper
            .POST({ url: logoutURL, isAuthHeader: true, withCredentials: false })
            .then((_) => {
                ApiActionHelper.logoutHelper();
            })
            .catch( async (_) => {
                await this.refreshToken();
                await AxiosHelper.POST({ url: logoutURL, isAuthHeader: true, withCredentials: false });
            })
            .finally(() => {
                Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
            });
    }

    static async signup({ email, password }) {
        if(!email || !password) {
            throw new Error("Invalid email / password while calling Axios Helper");
        }

        const url = Endpoints.signup;
        const body = PayloadHelper.forLoginAndSignup({ email, password, deviceId: LocalStorageHelper.getDeviceId() });

        console.log("calling : " + url);
        console.log("with : " + body);

        return await AxiosHelper
            .POST({ url, body, isAuthHeader: false, withCredentials: true })
            .then((response) => {
                ApiActionHelper.tokenResponseHelper(response);

                const { uid } = response.data;
                ApiActionHelper.loginSignupResponseHelper({ uid, email });

                return response;
            });
    }

    static async refreshToken() {
        const url = Endpoints.refreshToken;
        
        await AxiosHelper
            .POST({ url, isAuthHeader: false, withCredentials: true })
            .then((response) => {
                ApiActionHelper.tokenResponseHelper(response);

                return response;
            })
            .catch((error) => {
                ApiActionHelper.logoutHelper();
            });
    }

}

export default ApiCaller;