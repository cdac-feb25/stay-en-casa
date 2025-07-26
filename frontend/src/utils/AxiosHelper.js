import axios from "axios";
import { ApiGatewayEndpoint } from "./ApiGatewayEndpoints";
import PayloadHelper from "./PayloadHelper";

class AxiosHelper {

    static async login({ email, password }) {
        if(!email || !password) {
            throw new Error("Invalid email / password while calling Axios Helper");
        }
        
        axios.post(ApiGatewayEndpoint.login, PayloadHelper.signup({
            email: email,
            password: password,
            deviceId: deviceId
        }))
    }

}

export default AxiosHelper;