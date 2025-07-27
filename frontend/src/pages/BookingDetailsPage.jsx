/**
 * BookingDetailsPage displays the details of a single booking
 * using the bookingId retrieved from the URL parameters.
 */

import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { getBookingDetailsById } from '../services/bookingService';

const BookingDetailsPage = () => {

    // Get the bookingId from the URL (e.g., /booking/123)
    const { bookingId } = useParams();

    // State to hold booking details
    const [booking, setBooking] = useState(null);

    // State to handle loading and error states
    const [loading, setLoading] = useState(true);

    const [error, setError] = useState(null);

    /**
   * useEffect fetches booking details when the component mounts
   * or when the bookingId changes.
   */
    useEffect(()=> {
        const fetchBooking = async () => {
            try {

                // Fetch booking details from backend
                const response = await getBookingDetailsById(bookingId);
                setBooking(response);       // Set the booking data in state
            } catch (error) {
                setError("Failed to Fetch Booking Details!!!!!!!!");        // Show error if API call fails
            } finally{
                setLoading(false);      // Stop the loading spinner
            }
        };

        // Fetch only if bookingId is available
        if(bookingId){
            fetchBooking();
        }
        else{
            setError("Booking ID is Missing");
            setLoading(false);
        }
    }, [bookingId]);

    return (
        <div style={{padding : "1rem"}}>
            <h2>Booking Details</h2>

            {/* Show loading spinner */}
            {loading && <p>Loading.....</p>}
            
             {/* Show error message */}
            {error && <p style={{color: "red"}}>{error}</p>}

            {/* Show booking details if available */}
            {
                booking && (
                <div
                style={{
                border: '1px solid #ccc',
                padding: '1rem',
                borderRadius: '8px',
                backgroundColor: '#f4f4f4',
                }}>
                <p><strong>Booking ID: </strong>{booking.bookingId}</p>
                <p><strong>Buyer ID: </strong>{booking.buyerId}</p>
                <p><strong>Property ID: </strong>{booking.propertyId}</p>
                <p><strong>Booking Date: </strong>{booking.bookingDate}</p>
                <p><strong>Booking Status: </strong>{booking.status}</p>
                </div>
            )}
        </div>
    );
};

export default BookingDetailsPage;