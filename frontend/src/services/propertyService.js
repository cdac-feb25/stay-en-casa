// Property Service
// This service handles API calls related to properties.
import axios from "axios";

const BASE_URL = import.meta.env.VITE_PROPERTY_API_URL;

/**
 * Fetches all properties from the server.
 * @returns {Promise} - Promise resolving to the response of the API call.
 * If the response is not an array, it returns an empty array.
 * @throws {Error} - Throws an error if the API call fails.
 */
export const getAllProperties = async () => {
    try {
        const response = await axios.get(`${BASE_URL}`);
        return Array.isArray(response.data) ? response.data : [];
    } catch (error) {
        console.error("Error fetching all properties:", error);
        throw error;
    }
}