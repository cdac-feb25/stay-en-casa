
// Page to show bookings done by the logged-in user (buyer)

import React, { useEffect, useState } from "react";
import { getBookingDetailsByBuyerId } from "../services/bookingService";
import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton";
import SizedBox from "../components/SizedBox";
import Navbar from "../components/Navbar";

const MyBookingsPage = () => {

  // State to store all bookings made by the current buyer
  const [bookings, setBookings] = useState([]);

  // State to handle loading and error states
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Hook to navigate to other routes programmatically
  const navigate = useNavigate();

  /**
   * useEffect hook to fetch bookings for the logged-in buyer on component mount.
   */
  useEffect(() => {
    const fetchBookings = async () => {
      try {
        const buyerId = localStorage.getItem('userId');

        const response = await getBookingDetailsByBuyerId(buyerId);

        // Save the bookings to local state
        setBookings(response);
      } catch (error) {
        
        console.error('Failed to fetch bookings:', error);
      }
    };

    // Call booking fetch
    fetchBookings();
  }, []);

  /**
   * Navigate to booking details page using booking ID
   */
  const handleBookingClick = (bookingId) => {
    navigate(`/booking/${bookingId}`);
  };

  const getStatusColor = (status) => {
    if (status === "CONFIRMED" || status === "APPROVED") return "green";
    if (status === "CANCELLED") return "red";
    return "orange";
  };

  return (
    <>
      <Navbar />
      <Container style={{ maxWidth: 600, margin: "60px auto", padding: 24 }}>
        <Column align="center">
          <h2>My Bookings</h2>
          <SizedBox height={16} />
          {loading && <p>Loading...</p>}
          {error && <p style={{ color: "red" }}>{error}</p>}
          {!loading && bookings.length === 0 && (
            <p style={{ color: "red" }}>No Bookings Found!</p>
          )}
          {!loading && bookings.length > 0 && (
            <Column align="stretch" style={{ width: "100%" }}>
              {bookings.map((booking) => (
                <Row
                  key={booking.bookingId}
                  style={{
                    border: "1px solid #ccc",
                    padding: "1rem",
                    borderRadius: "8px",
                    marginBottom: "1rem",
                    backgroundColor: "#f7f7f7",
                    cursor: "pointer",
                    alignItems: "center",
                  }}
                  onClick={() => handleBookingClick(booking.bookingId)}
                >
                  <Column align="flex-start" style={{ flex: 1 }}>
                    <div><strong>Property ID:</strong> {booking.propertyId}</div>
                    <div><strong>Date:</strong> {booking.bookingDate}</div>
                    <div>
                      <strong>Status:</strong>{" "}
                      <span style={{ color: getStatusColor(booking.status), fontWeight: 600 }}>
                        {booking.status}
                      </span>
                    </div>
                  </Column>
                  <CustomButton
                    variant="outlined"
                    color="secondary"
                    onClick={e => {
                      e.stopPropagation();
                      navigate(`/update-booking/${booking.bookingId}`);
                    }}
                  >
                    Update
                  </CustomButton>
                </Row>
              ))}
            </Column>
          )}
        </Column>
      </Container>
    </>
  );
};

export default MyBookingsPage;
