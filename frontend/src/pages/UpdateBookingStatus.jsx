import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getBookingDetailsById, updateBookingStatusByBookingId } from "../services/bookingService";

/**
 * UpdateBookingStatus component allows the admin to update the status of a booking.
 * It fetches the booking details using the bookingId from the URL,
 * allows the admin to select a new status from a dropdown,
 * and submits the updated status back to the backend.
*/
const UpdateBookingStatus = () => {

    const { bookingId } = useParams();

    const navigate = useNavigate();

    const [booking, setBooking] = useState(null);

    const [status, setStatus] = useState('');

    // Fetch booking details by ID when component mounts
    useEffect(
        () => {
            const fetchBooking = async () => {
                try {
                    const response = await getBookingDetailsById(bookingId);
                    setBooking(response);
                    setStatus(response.status);     // Set current status in dropdown
                }catch(error) {
                    console.error('Failed to Fetch Booking: ', error);
                }
            };

            fetchBooking();
        }, [bookingId]
    );

    // Handle status change from dropdown
    const handleStatusChange = (e) => {
        setStatus(e.target.value);
    };

    // Handle form submission to update booking status
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await updateBookingStatusByBookingId(bookingId, status);
            alert('Booking Status Updated Successfully!!!');
            navigate('/my-bookings');       // Redirect to My Bookings page after update
        } catch (error) {
            console.error('Failed to Update Booking Status: ', error);
            alert('Something went wrong...');
            
        }
    };

    if(!booking) return <p>Loading Booking Details....</p>

    return (
        <div>
            <h2>Update Booking Status</h2>

            <p><strong>Booking ID: </strong>{booking.bookingId}</p>
            <p><strong>Property ID: </strong>{booking.propertyId}</p>
            <p><strong>Buyer ID: </strong>{booking.buyerId}</p>
            <p><strong>Booking Date: </strong>{booking.bookingDate}</p>
            <p><strong>Current Status: </strong>{booking.status}</p>

            { /* Form to update booking status */}
            <form onSubmit={handleSubmit}>
                <label htmlFor="status">Select Status: </label>
                <select id="status" value={status} onChange={handleStatusChange} required>
                    <option value="Pending">Pending</option>
                    <option value="Confirmed">Confirmed</option>
                    <option value="Cancelled">Cancelled</option>
                </select>

                <br /> <br />
                <button type="submit">Update Status</button>
            </form>
        </div>
    )
}

export default UpdateBookingStatus;