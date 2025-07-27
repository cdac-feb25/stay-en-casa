import * as React from 'react';
import Colors from './utils/Colors';
import Login from './pages/Login';
import './assets/fonts/nunito-bold.css';
import Signup from './pages/Signup';
import { Route, Router, Routes } from 'react-router-dom';
import BookingPage from './pages/BookingPage';
import MyBookingsPage from './pages/MyBookingsPage';
import Navbar from './components/Navbar';
import BookingDetailsPage from './pages/BookingDetailsPage';
import UpdateBookingStatus from './pages/UpdateBookingStatus';

function App() {
  React.useEffect(() => {
    document.body.style.background = Colors.background;
    document.body.style.color = Colors.primary;
    document.body.style.fontFamily = 'Nunito, sans-serif';
  }, []); // Run only once on mount

  return (
    <>
    <Navbar />
    <Routes>
      <Route path="/" element={<BookingPage />} />
      {/* <Route path="/" element={<Home />} /> */}
      {/* <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} /> */}
      {/* <Route path="/account" element={<Account />} /> */}
      <Route path="/my-bookings" element={<MyBookingsPage />} />
      <Route path="/booking/:bookingId" element={<BookingDetailsPage />} />
      <Route path='/update-booking/:bookingId' element={<UpdateBookingStatus />} />
    </Routes>
    </>
  )
}

export default App;
