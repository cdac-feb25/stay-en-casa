import { signupInputFieldIds } from "../pages/Signup";
import AxiosHelper from "../utils/AxiosHelper";

class SignupHelper {
    
    static async handleSignup() {
        const email = document.getElementById(signupInputFieldIds.email).value;
        const password = document.getElementById(signupInputFieldIds.password).value;
    
        console.log(`email: ${email}`);
        console.log(`password: ${password}`);
    
        AxiosHelper.login();
    }

}


export default SignupHelper;