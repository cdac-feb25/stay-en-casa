//Booking Page
// Page to create a new Booking using form

import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { createBooking } from "../services/bookingService";
import Navbar from "../components/Navbar";
import Container from "../components/Container";
import CustomButton from "../components/CustomButton";
import Column from "../components/Column";
import SizedBox from "../components/SizedBox";
import Row from "../components/Row";

/**
 * BookingPage component is responsible for handling the creation of a new booking.
 * It accepts buyerId and propertyId (typically passed from context or props)
 * and sends a booking request to the backend using the createBooking API.
 */

const BookingPage = () => {
    const { propertyId } = useParams();
    const [buyerId, setBuyerId] = useState(null);
    const [bookingStatus, setBookingStatus] = useState(null);
    const [loadingStatus, setLoadingStatus] = useState(false);
    const [bookingId, setBookingId] = useState(null);
    const navigate = useNavigate();

    // On mount, check if user is logged in; if not, redirect to login
    useEffect(() => {
        const storedBuyerId = localStorage.getItem("userId");
        if (storedBuyerId) {
            setBuyerId(storedBuyerId);
        } else {
            setBookingStatus("Please login to continue.");
            setTimeout(() => navigate("/login"), 1500);
        }
    }, [navigate]);

   /**
   * Handles booking submission by preparing the booking payload
   * and calling the createBooking API. It also manages the loading state
   * and displays the result to the user.
   */

    const handleBooking = async () => {
        if (!buyerId || !propertyId) {
            setBookingStatus("Missing Booking Details");
            return;
        }
        const bookingDetails = {
            buyerId,
            propertyId,
            bookingDate: new Date().toISOString().split("T")[0],
            status: "PENDING",
        };

        try {
            setLoadingStatus(true);
            const response = await createBooking(bookingDetails);
            setBookingStatus("Booking Successful!");
            setBookingId(response?.bookingId);
            console.log("Booking Response: ", response);
        } catch (error) {
            setBookingStatus("Booking failed. Please Try Again!");
        } finally {
            setLoadingStatus(false);
        }
    };

    return (
        <>
            <Navbar />
            <Container style={{ maxWidth: 400, margin: "60px auto", padding: 24 }}>
                <Column align="center">
                    <h2>Book This Property</h2>
                    <SizedBox height={16} />
                    <CustomButton
                        onClick={handleBooking}
                        disabled={loadingStatus}
                        style={{ width: "100%" }}
                    >
                        {loadingStatus ? "Booking In Progress..." : "Confirm Booking"}
                    </CustomButton>
                    <SizedBox height={16} />
                    {bookingStatus && (
                        <Row justify="center">
                            <span
                                style={{
                                    color: bookingStatus.includes("Successful") ? "green" : "red",
                                    fontWeight: 500,
                                }}
                            >
                                {bookingStatus}

                                {bookingId && bookingStatus.includes("Successful") && (
                                    <span style={{ display: "block", marginTop: 8 }}>
                                        Booking ID: <b>{bookingId}</b>
                                    </span>
                                )}
                            </span>
                        </Row>
                    )}
                    {bookingId && (
                        <Row justify="center">
                            <CustomButton
                                variant="outlined"
                                color="secondary"
                                onClick={() => navigate(`/booking/${bookingId}`)}
                                style={{ width: "100%", marginTop: 12 }}
                            >
                                View Booking Details
                            </CustomButton>
                        </Row>
                    )}
                </Column>
            </Container>
        </>
    );
};

export default BookingPage