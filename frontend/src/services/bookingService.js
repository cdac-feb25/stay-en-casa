// Booking Service
//Service for handling booking-related API calls
import axios from 'axios';

const BASE_URL = import.meta.env.VITE_BOOKING_API_URL;

/** 
 * Fetches all bookings from the server.
 * @returns {Promise} - Promise resolving to the response of the API call.
*/
export const getAllBookings = async () => {
    try{
        const response = await axios.get(`${BASE_URL}`);
        return response.data;
    }catch (error) {
        console.error("Error fetching bookings:", error);
        throw error;
    }
};

/**
 * Function to create a new booking.
 * @param {Object} bookingDetails - The details of the booking to be submitted.
 * @returns {Promise} - Promise resolving to the response of the API call.
 */
export const createBooking = async (bookingDetails) => {
    try {
        console.log("Full env:", import.meta.env);
        console.log("BASE_URL:", BASE_URL);
        const response = await axios.post(`${BASE_URL}`, bookingDetails);
        return response.data;
    } catch (error) {
        console.error("Error creating Booking:", error);
        throw error;
    }
};

/**
 * Get specific Booking details by Booking ID.
 * @param {string} bookingId - The ID of the booking to be fetched.
 * @returns {Promise} - Promise resolving to the response of the API call.
 */
export const getBookingDetailsById = async (bookingId) =>{
    try {
        const reponse = await axios.get(`${BASE_URL}/${bookingId}`);
        return reponse.data;
    } catch (error) {
        console.error("Error fetching Booking details by ID: ", error);
        throw error;
    }
};

/**
 * Get all the bookings made by a specific buyer.
 * @param {string} buyerId - The ID of the buyer whose bookings are to be fetched.
 * @returns {Promise} - Promise resolving to the response of the API call. 
 */
export const getBookingDetailsByBuyerId = async (buyerId) => {
    try {
        const response = await axios.get(`${BASE_URL}/buyer/${buyerId}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching all the Bookings made by Buyer: ", error);
        throw error;
    }
};

/** 
 * Get all bookings for a specific property.
 * @param {string} propertyId - The ID of the property whose bookings are to be fetched.
 * @returns {Promise} - Promise resolving to the response of the API call.
*/
export const getBookingDetailsByPropertyId = async (propertyId) => {
    try {
        const response = await axios.get(`${BASE_URL}/property/${propertyId}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching Bookings made for a Property: ",error);
        throw error;
    }
}    

/**
 * Update the status of a booking by its ID.
 * @param {string} bookingId - The ID of the booking to be updated.
 * @param {string} status - The new status to be set for the booking (PENDING, APPROVED, CANCELLED).
 * @returns {Promise} - Promise resolving to the response of the API call.
 */
export const updateBookingStatusByBookingId = async (bookingId, status) => {
    try {
        const response = await axios.put(`${BASE_URL}/${bookingId}/status`, { status });
        return response.data;
    } catch (error) {
        console.error("Error updating Booking Status of a Property: ",error);
        throw error;
    }
};