import React, { useState } from 'react';
import BookingPage from '../pages/bookings/BookingPage';
// import logo from '../assets/ChatGPT Image Jul 23, 2025, 01_55_52 PM.png';
import './App.css';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Routes>
      <Route path="/" element={<BookingPage />} />
      {/* <Route path="/booking/:propertyId" element={<BookingPage />} /> ---> Setup Route Dynamic Property ID while deploying */} 
      {/* <Route path="/login" element={<LoginPage />} /> */}
    </Routes>
  )
}

export default App;
