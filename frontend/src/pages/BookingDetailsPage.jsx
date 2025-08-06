/**
 * BookingDetailsPage displays the details of a single booking
 * using the bookingId retrieved from the URL parameters.
 */

import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import { getBookingDetailsById } from '../services/bookingService';
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import SizedBox from "../components/SizedBox";
import CustomButton from "../components/CustomButton";
import Colors from '../utils/Colors';

const BookingDetailsPage = () => {

    // Get the bookingId from the URL (e.g., /booking/123)
    const { bookingId } = useParams();
    
    const navigate = useNavigate();

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
                setBooking(response);       
            } catch (error) {
                setError("Failed to Fetch Booking Details!!!!!!!!");        
            } finally{
                setLoading(false);      
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

    const getStatusColor = (status) => {
        if (status === "CONFIRMED" || status === "APPROVED") return "green";
        if (status === "CANCELLED") return "red";
        return "orange";
    };

    return (
        <>
            <Container style={{ maxWidth: 800, margin: "60px auto", padding: 24 }}>
                <Column align="center" >
                    <h2 style = {{color: Colors.textOrange}}>Booking Details</h2>
                    <SizedBox height={16} />
                    {/* Loading State */}
                    {loading && <p>Loading...</p>}
                    {/* Error State */}
                    {error && <p style={{ color: "red" }}>{error}</p>}
                    {/* Booking Details */}
                    {booking && (
                        <Column align="flex-start" style={{
                            border: '1px solid #ccc',
                            padding: '1rem',
                            borderRadius: '8px',
                            backgroundColor: '#f4f4f4',
                            width: "100%"
                        }}>
                            <Row>
                                <strong style = {{color: Colors.textOrange}}>Booking ID:</strong>&nbsp;{booking.bookingId}
                            </Row>
                            <Row>
                                <strong style = {{color: Colors.textOrange}}>Buyer ID:</strong>&nbsp;{booking.buyerId}
                            </Row>
                            <Row>
                                <strong style = {{color: Colors.textOrange}}>Property ID:</strong>&nbsp;{booking.propertyId}
                            </Row>
                            <Row>
                                <strong style = {{color: Colors.textOrange}}>Booking Date:</strong>&nbsp;{booking.bookingDate}
                            </Row>
                            <Row>
                                <strong style = {{color: Colors.textOrange}}>Booking Status:</strong>&nbsp;
                                <span style={{ color: getStatusColor(booking.status), fontWeight: 600 }}>
                                    {booking.status}
                                </span>
                            </Row>
                        </Column>
                    )}
                    <SizedBox height={24} />
                    {/* Back Button */}
                    <CustomButton
                        variant="outlined"
                        color="secondary"
                        onPress={() => navigate(-1)}
                        style={{ width: "100%" }}
                    >
                        Back
                    </CustomButton>
                </Column>
            </Container>
        </>
    );
};

export default BookingDetailsPage;