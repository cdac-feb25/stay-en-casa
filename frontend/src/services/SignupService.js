import ApiCaller from "./ApiCaller";
import AppRoutes from "../utils/AppRoutes";
import Navigate from "./NavigationService";
import { getResponseError } from "../types/ResponseError";

function handleSignupFormSubmit(setIsLoading, setErrorMsg, setShowError) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        setIsLoading(true);
        event.preventDefault();

        const form = event.target;
        const formData = new FormData(form);

        const { email, password, confirmPassword } = Object.fromEntries(formData.entries());

        /**
         * TODO:
         * - if signup fail
         * - if signup is successful
         * -- create profile 
         */
        if(password === confirmPassword) {
            await ApiCaller.signup({ email, password })
                .then((_) => {
                    setShowError(true);
                    // navigate(AppRoutes.editProfile);
                    Navigate.to({ path: AppRoutes.editProfile, clearBrowserStack: true });
                })
                .catch((error) => {
                    const resError = getResponseError(error);

                    setErrorMsg(resError.errorMessage);
                    setShowError(true);
                });
        }

        setIsLoading(false);
    };
}

export default handleSignupFormSubmit;