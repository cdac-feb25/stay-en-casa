import { v4 as randomUuid } from 'uuid';

class LocalStorageHelper {
    static #deviceIdKey = "device_id"; 
    static #jwtTokenKey = "jwt_token"; 

    static getDeviceId() {
        const deviceId = localStorage.getItem(LocalStorageHelper.#deviceIdKey);

        if(!deviceId) {
            deviceId = randomUuid();
            localStorage.setItem(LocalStorageHelper.#deviceIdKey, deviceId);
        }

        return deviceId;
    }

}