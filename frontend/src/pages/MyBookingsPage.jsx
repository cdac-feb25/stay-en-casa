
// Page to show bookings done by the logged-in user (buyer)

import React, { useEffect, useState } from "react";
import { getBookingDetailsByBuyerId } from "../services/bookingService";
import { useNavigate } from "react-router-dom";     // React Router's navigation hook

const MyBookingsPage = () => {

  // State to store all bookings made by the current buyer
  const [bookings, setBookings] = useState([]);

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

  return (
    <div>
      <h2>My Bookings</h2>
      {bookings.length === 0 ? (
        <p style={{color: "red"}}>No Bookings Found!!!!</p>
      ) : (
        <ul style={{ listStyleType: "none", paddingLeft: 0 }}>
          {bookings.map((booking) => (
            <li
              key={booking.bookingId}
              onClick={() => handleBookingClick(booking.bookingId)}
              style={{
                border: "1px solid #ccc",
                padding: "1rem",
                borderRadius: "8px",
                marginBottom: "1rem",
                cursor: "pointer",
                backgroundColor: "#f7f7f7",
              }}
            >
              <strong>Property ID:</strong> {booking.propertyId} <br />
              <strong>Date:</strong> {booking.bookingDate} <br />
              <strong>Status:</strong> {booking.status}
              <button onClick={(e) =>
                {e.stopPropagation(); // Prevent click from bubbling up to the li
                navigate(`/update-booking/${booking.bookingId}`);}}>Update</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default MyBookingsPage;
