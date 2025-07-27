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
                justifyContent: "space-between",
                alignItems: "center",
                background: "linear-gradient(90deg, rgb(251,242,222) 70%)",
                boxShadow: "0 2px 8px rgba(0,0,0,0.07)" }}>
    <div style={{ marginLeft: "1.5rem" }}>
      <Link to="/">Booking</Link> |{" "}
      <Link to="/my-bookings">My Bookings</Link>
    </div>
    <img src={logo} alt="Logo" style={{height: "50px"}} />
    </nav>
  );
};

export default Navbar;
