
class PayloadHelper {
    static forLoginAndSignup({email, password, deviceId}) {
        return {
            "email": email,
            "password": password,
            "deviceId": deviceId,
        };
    }

    // static signup({ email, password, deviceId }) {
    //     return {
    //         "email": email,
    //         "password": password,
    //         "deviceId": deviceId,
    //     };
    // }
}

export default PayloadHelper;