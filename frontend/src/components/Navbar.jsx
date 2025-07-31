import React from "react";
import { Link } from "react-router-dom";
import logo from "../assets/ChatGPT Image Jul 23, 2025, 01_55_52 PM.png"

const Navbar = () => {
  return (
    <nav style={{ position: "fixed", 
                top: 0, 
                left: 0, 
                width: "100%",  
                zIndex: 1000,
                display: "flex",
                justifyContent: "normal",
                alignItems: "center",
                background: "linear-gradient(90deg, rgb(251,242,222) 70%)",
                boxShadow: "0 2px 8px rgba(0,0,0,0.07)" }}>
    <img src={logo} alt="Logo" style={{height: "50px"}} />
    <div >
      <Link to="/">Booking</Link> |{" "}
      <Link to="/my-bookings">My Bookings</Link> |{" "}
      <Link to="/properties">All Properties</Link>
    </div>
    
    </nav>
  );
};

export default Navbar;
