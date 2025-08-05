//Booking Page
// Page to create a new Booking using form

import React, { useEffect, useState } from "react";
import { useParams, useNavigate, useLocation } from "react-router-dom";
import { createBooking } from "../services/bookingService";
import { getPropertyDetailsById } from "../services/propertyService"; 
import Container from "../components/Container";
import CustomButton from "../components/CustomButton";
import Column from "../components/Column";
import SizedBox from "../components/SizedBox";
import Row from "../components/Row";
import Colors from "../utils/Colors";
import UserContext from "../utils/UserContext";
import AppRoutes from "../utils/AppRoutes";

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
  const [property, setProperty] = useState(null); 

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const user = UserContext.getLoggedInUser();
    if (user && user.uid) {
      setBuyerId(user.uid);
    } else {
        // If not logged in, send to login with redirect back to this booking page
      navigate(AppRoutes.login,{ state: { from: location.pathname } });
    }

    const fetchProperty = async () => {
      try {
        const propertyData = await getPropertyDetailsById(propertyId);
        setProperty(propertyData);
      } catch (error) {
        console.error("Failed to fetch property details:", error);
      }
    };
    fetchProperty();
  }, [navigate, location, propertyId]);

  const handleBooking = async () => {
    if (!buyerId || !propertyId) {
      setBookingStatus("Missing booking details.");
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
    } catch (error) {
      setBookingStatus("Booking failed. Please try again!");
    } finally {
      setLoadingStatus(false);
    }
  };

  return (
    <Container style={{ maxWidth: 500, margin: "60px auto", padding: 24 }}>
      <Column align="center">
        <h2 style={{ color: Colors.textOrange }}>Book This Property</h2>
        <SizedBox height={20} />

        {/* Show property details if available */}
        {property ? (
          <div
            style={{
              border: `2px solid ${Colors.textOrange}`,
              borderRadius: "12px",
              padding: "16px",
              width: "100%",
              marginBottom: "20px",
              backgroundColor: "#fff8f0",
            }}
          >
            <h3 style={{ margin: "0 0 12px 0", color: Colors.textOrange }}>
              {property.propertyName}
            </h3>
            <p style={{ margin: "4px 0" }}>
              <b>Category:</b> {property.propertyCategory}
            </p>
            <p style={{ margin: "4px 0" }}>
              <b>Price:</b> ₹{property.price}
            </p>
            <p style={{ margin: "4px 0" }}>
              <b>Location:</b> {property.location?.city}, {property.location?.state}
            </p>
          </div>
        ) : (
          <p>Loading property details...</p>
        )}

        <CustomButton
          onClick={handleBooking}
          disabled={loadingStatus}
          style={{ width: "100%" }}
        >
          {loadingStatus ? "Booking in Progress..." : "Confirm Booking"}
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
              onPress={() => navigate(AppRoutes.bookingById)}
              style={{ width: "100%", marginTop: 12 }}
            >
              View Booking Details
            </CustomButton>
          </Row>
        )}
      </Column>
    </Container>
  );
};

export default BookingPage;
