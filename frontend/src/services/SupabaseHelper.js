import { createClient } from "@supabase/supabase-js";
import UserContext from "../utils/UserContext";

const supabaseUrl = import.meta.env.VITE_SUPABASE_URL;
const supabaseStoragePublicUrl = import.meta.env.VITE_SUPABASE_STORAGE_PUBLIC_URL;
const supabaseAnonPublicKey = import.meta.env.VITE_SUPABASE_PUBLIC_KEY;

class SupabaseHelper {
    
    static #supabase = createClient(supabaseUrl, supabaseAnonPublicKey);
    static #bucketId = 'stay-en-casa';
    static #profilePhotoFolderName = 'profile-photos';
    
    /**
     * @param {File} file 
     */
    static async uploadProfilePhotoFile(file) {
        const fileExtension = this.#getFileExtension(file);
        const uid = UserContext.getLoggedInUser().uid;
        const pathWithFilename = `${this.#profilePhotoFolderName}/${uid}.${fileExtension}`;

        console.log("pathWithFilename : ", pathWithFilename);

        const { data, error } = await this.#supabase
            .storage
            .from(this.#bucketId)
            .upload(pathWithFilename, file, { 
                upsert: true, 
                contentType: file.type,
                // cacheControl: "3600"  // default
            });

        if(error) {
            console.log(error);

            return null;
        } else {
            const { data: { publicUrl } } = this.#supabase
                .storage
                .from(this.#bucketId)
                .getPublicUrl(pathWithFilename);

            if(publicUrl == null || publicUrl.trim().length == 0) {
                publicUrl = `${supabaseStoragePublicUrl}/${pathWithFilename}`;
            }

            return publicUrl;
        }
    }

    /**
     * @param {File} file 
     * @returns {string} extension
     */
    static #getFileExtension(file) {
        const type = file.type;
        return type.substring(6);
    } 

}

export default SupabaseHelper;
