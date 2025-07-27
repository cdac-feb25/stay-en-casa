//Booking Page
// Page to create a new Booking using form

import React, { useEffect, useState } from "react";
import { createBooking } from "../../services/bookingService";
import { useParams } from "react-router-dom";

/**
 * BookingPage component is responsible for handling the creation of a new booking.
 * It accepts buyerId and propertyId (typically passed from context or props)
 * and sends a booking request to the backend using the createBooking API.
 */

const BookingPage = () => {
  
    // const { propertyId } = useParams(); //Get PropertyID from URL
    const propertyId = "prop-23456";
    const [buyerId, setBuyerId] = useState(null);
    const [bookingStatus, setBookingStatus] = useState(null);
    const [loadingStatus, setLoadingStatus] = useState(false);

    useEffect(() => {

        const storedBuyerId = localStorage.getItem("userId");    //Deployment code
        if(storedBuyerId) {
            setBuyerId(storedBuyerId);
        }
        else{
            console.log("You must be logged in to Book a Property");
            setBookingStatus("Please login to continue.");
        }
    }, []
    );

    /**
   * Handles booking submission by preparing the booking payload
   * and calling the createBooking API. It also manages the loading state
   * and displays the result to the user.
   */

    const handleBooking = async () => {

        if(!buyerId || !propertyId)
        {
            setBookingStatus("Missing Booking Details");
            return;
        }
        const bookingDetails = {
            buyerId,
            propertyId,
            bookingDate: new Date().toISOString().split("T")[0],
            status: 'PENDING',
        };

        try {
            setLoadingStatus(true);
            const response = await createBooking(bookingDetails);
            setBookingStatus('Booking Successful!!!!!');
            console.log('Booking Response: ',response);
        } catch (error) {
            setBookingStatus('Booking failed. Please Try Again!!!!!');
        }finally{
            setLoadingStatus(false);
        }
    };
    return (
    <div>
        <h2>Book This Property</h2>
        <button onClick={handleBooking} disabled = {loadingStatus}>{loadingStatus ? 'Booking In Progress...':'Confirm Booking'}</button>
        {bookingStatus && <p>{bookingStatus}</p>}
    </div>
  )
};

export default BookingPage