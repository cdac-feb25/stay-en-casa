import ApiCaller from "./ApiCaller";
import ApiActionHelper from "../utils/ApiActionHelper";
import { getResponseError } from "../types/ResponseError";
import SupabaseHelper from "./SupabaseHelper";
import { isObjEmpty } from "./JsUtils";

function handleUpdateProfile(file, profile, setProfile, setProfileUpdateError, setShowProgress) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        setShowProgress(true);
        event.preventDefault();

        console.log("file : ", file);
        console.log("isObjEmpty : ", isObjEmpty(file));
        console.log("profile :", profile);

        if(!isObjEmpty(file)) {
            const photoUrl = await SupabaseHelper.uploadProfilePhotoFile(file);

            console.log(`photoUrl : ${photoUrl}`);

            if(photoUrl) {
                setProfileUpdateError("");
                
                setProfile(function(prev) {
                    return {
                        ...prev,
                        photoUrl
                    };
                });

                await updateUserProfile(profile);
            } else {
                setProfileUpdateError("Oops !!! Something went wrong. Please refresh and try again later.");
            }
        } else {
            await updateUserProfile(profile);
        }
        
        setShowProgress(false);
    }
}

async function updateUserProfile(profile) {
    await ApiCaller.updateProfile(profile)
    .then((response) => {
        console.log("success : " + response);
        window.location.reload();
    })
    .catch( async (error) => {
        const resError = getResponseError(error);

        // ApiActionHelper.Error.accessTokenErrorAction(resError.errorCode, );
        if(resError.errorCode === 1002) {
            await ApiCaller.refreshToken();

            await ApiCaller.updateProfile(profile)
            .then((response) => {
                console.log("success : " + response);
                window.location.reload();
            })
            .catch((_) => {
                console.log("error from service : " + error);
                const resError = ApiActionHelper.getErrorObject(error);
                setProfileUpdateError(resError.message);
                
                // TODO: remove timeout in release
                setTimeout(() => {
                    ApiCaller.logout();
                }, 1500);
            });
        }
    });
}

export { handleUpdateProfile };