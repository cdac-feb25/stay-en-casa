import ApiCaller from "./ApiCaller";
import AppRoutes from "../utils/AppRoutes";

function handleSignupFormSubmit(navigate, setErrorMsg, setShowError) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        event.preventDefault();

        const form = event.target;
        const formData = new FormData(form);

        const { email, password, confirmPassword } = Object.fromEntries(formData.entries);

        /**
         * TODO:
         * - if signup fail
         * - if signup is successful
         * -- create profile 
         */
        if(password === confirmPassword) {
            await ApiCaller.signup()
                .then((response) => {
                    navigate(AppRoutes.editProfile);
                })
                .catch((error) => {

                });
        }
    };
}

export default handleSignupFormSubmit;