import { v4 as randomUuid } from 'uuid';
import { User } from '../types/User';

class LocalStorageHelper {
    
    static #uidKey = "uid"; 
    static #deviceIdKey = "device_id"; 
    static #jwtAccessTokenKey = "access_token"; 
    static #userProfile = "user_profile";

    static clearUid() {
        localStorage.removeItem(this.#uidKey);
    }
    static clearDeviceId() {
        localStorage.removeItem(this.#deviceIdKey);
    }
    static clearJwtAccessToken() {
        localStorage.removeItem(this.#jwtAccessTokenKey);
    }
    static clearUserProfile() {
        localStorage.removeItem(this.#userProfile);
    }

    // static getUid() {
    //     return localStorage.getItem(this.#uidKey);
    // }

    /**
     * @param {string} uid
     */
    static setUid(uid) {
        localStorage.setItem(this.#uidKey, uid);
    }

    /**
     * Get Device ID,
     * if not found create a new device id 
     * 
     * @returns {string} deviceId
     */
    static getDeviceId() {
        let deviceId = localStorage.getItem(this.#deviceIdKey);

        if(!deviceId) {
            deviceId = randomUuid();
            localStorage.setItem(this.#deviceIdKey, deviceId);
        }

        return deviceId;
    }

    /**
     * 
     * @returns {string} JWT Access-Token
     */
    static getJwtAccessToken() {
        return localStorage.getItem(this.#jwtAccessTokenKey);
    }

    /**
     * @param {string} accessToken
     */
    static setJwtAccessToken(accessToken) {
        localStorage.setItem(this.#jwtAccessTokenKey, accessToken);
    }

    /**
     * fetching user profile and parsing 
     * 
     * @returns {User} profile
     */
    static getUserProfile() {
        const profile = localStorage.getItem(this.#userProfile);

        if(profile) {
            return JSON.parse(profile);
        }
        return null;
    }
    
    /**
     * 
     * @param {Object} profile
     */
    static setUserProfile(profile) {
        const profileStr = JSON.stringify(profile);

        localStorage.setItem(this.#userProfile, profileStr);
    }

}

export default LocalStorageHelper;