// //Booking Page
// // Page to create a new Booking using form

// import React, { useEffect, useState } from "react";
// import { useParams, useNavigate, useLocation } from "react-router-dom";
// import { createBooking } from "../services/bookingService";
// import { getPropertyDetailsById } from "../services/propertyService"; 
// import Container from "../components/Container";
// import CustomButton from "../components/CustomButton";
// import Column from "../components/Column";
// import SizedBox from "../components/SizedBox";
// import Row from "../components/Row";
// import Colors from "../utils/Colors";
// import UserContext from "../utils/UserContext";
// import AppRoutes from "../utils/AppRoutes";

// /**
//  * BookingPage component is responsible for handling the creation of a new booking.
//  * It accepts buyerId and propertyId (typically passed from context or props)
//  * and sends a booking request to the backend using the createBooking API.
//  */

// const BookingPage = () => {
//   const { propertyId } = useParams();
//   const [buyerId, setBuyerId] = useState(null);
//   const [bookingStatus, setBookingStatus] = useState(null);
//   const [loadingStatus, setLoadingStatus] = useState(false);
//   const [bookingId, setBookingId] = useState(null);
//   const [property, setProperty] = useState(null); 

//   const navigate = useNavigate();
//   const location = useLocation();

//   useEffect(() => {
//     const user = UserContext.getLoggedInUser();
//     if (user && user.uid) {
//       setBuyerId(user.uid);
//     } else {
//         // If not logged in, send to login with redirect back to this booking page
//       navigate(AppRoutes.login,{ state: { from: location.pathname } });
//     }

//     const fetchProperty = async () => {
//       try {
//         const propertyData = await getPropertyDetailsById(propertyId);
//         setProperty(propertyData);
//       } catch (error) {
//         console.error("Failed to fetch property details:", error);
//       }
//     };
//     fetchProperty();
//   }, [navigate, location, propertyId]);

//   const handleBooking = async () => {
//     if (!buyerId || !propertyId) {
//       setBookingStatus("Missing booking details.");
//       return;
//     }

//     const bookingDetails = {
//       buyerId,
//       propertyId,
//       bookingDate: new Date().toISOString().split("T")[0],
//       status: "PENDING",
//     };

//     try {
//       setLoadingStatus(true);
//       const response = await createBooking(bookingDetails);
//       setBookingStatus("Booking Successful!");
//       setBookingId(response?.bookingId);
//     } catch (error) {
//       setBookingStatus("Booking failed. Please try again!");
//     } finally {
//       setLoadingStatus(false);
//     }
//   };

//   return (
//     <Container style={{ maxWidth: 800, margin: "60px auto", padding: 24 }}>
//       <Column align="center">
//         <h2 style={{ color: Colors.textOrange }}>Book This Property</h2>
//         <SizedBox height={20} />

//         {/* Show property details if available */}
//         {property ? (
//           <div
//             style={{
//               border: `2px solid `,
//               borderRadius: "12px",
//               padding: "16px",
//               width: "100%",
//               marginBottom: "20px",
//               backgroundColor: "#fff8f0",
//             }}
//           >
//             <h3 style={{ margin: "0 0 12px 0", color: Colors.textOrange }}>
//               {property.propertyName}
//             </h3>
//             <p style={{ margin: "4px 0" }}>
//               <b>Category:</b> {property.propertyCategory}
//             </p>
//             <p style={{ margin: "4px 0" }}>
//               <b>Price:</b> ₹{property.price}
//             </p>
//             <p style={{ margin: "4px 0" }}>
//               <b>Location:</b> {property.location?.city}, {property.location?.state}
//             </p>
//           </div>
//         ) : (
//           <p style={{color: Colors.textOrange}}>Loading property details...</p>
//         )}

//         <CustomButton
//           title="Book Now"
//           onPress={handleBooking}
//           disabled={loadingStatus}
//           style={{ width: "100%" }}
//         >
//           {loadingStatus ? "Booking in Progress..." : "Confirm Booking"}
//         </CustomButton>
//         <SizedBox height={16} />

//         {bookingStatus && (
//           <Row justify="center">
//             <span
//               style={{
//                 color: bookingStatus.includes("Successful") ? "green" : "red",
//                 fontWeight: 500,
//               }}
//             >
//               {bookingStatus}
//               {bookingId && bookingStatus.includes("Successful") && (
//                 <span style={{ display: "block", marginTop: 8 }}>
//                   Booking ID: <b>{bookingId}</b>
//                 </span>
//               )}
//             </span>
//           </Row>
//         )}

//         {bookingId && (
//           <Row justify="center">
//             <CustomButton
//               title="View Booking Details"
//               variant="outlined"
//               color="secondary"
//               onPress={() => navigate(AppRoutes.bookingById)}
//               style={{ width: "100%", marginTop: 12 }}
//             >
//               View Booking Details
//             </CustomButton>
//           </Row>
//         )}
//       </Column>
//     </Container>
//   );
// };

// export default BookingPage;
import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import Colors from "../utils/Colors";

const BookingPage = () => {
  const { propertyId } = useParams();
  const navigate = useNavigate();

  const handleBooking = () => {
    alert(`Booking successful for property: ${propertyId}`);
    navigate("/my-properties"); // Redirect after booking
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "90vh" }}>
      <div style={{
        backgroundColor: "#111827",
        padding: "30px",
        borderRadius: "20px",
        color: "white",
        boxShadow: "0px 0px 15px rgba(0,0,0,0.3)",
        minWidth: "350px",
        textAlign: "center",
      }}>
        <h2 style={{ color: Colors.textOrange }}>Book This Property</h2>
        <p>Property ID: {propertyId}</p>
        <button
          onClick={handleBooking}
          style={{
            backgroundColor: Colors.textOrange,
            border: "none",
            color: "white",
            padding: "10px 20px",
            borderRadius: "8px",
            cursor: "pointer",
            marginTop: "20px"
          }}
        >
          Confirm Booking
        </button>
      </div>
    </div>
  );
};

export default BookingPage;
