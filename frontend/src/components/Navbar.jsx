import React from "react";
import { Link } from "react-router-dom";
import logo from "../assets/stay_en_casa-nobg.png";
import "../components/Navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      {/* Logo */}
      <img src={logo} alt="Logo" className="navbar-logo" />

      {/* Links */}
      <div className="navbar-links">
        <Link to="/" className="nav-link">Booking</Link>
        <Link to="/my-bookings" className="nav-link">My Bookings</Link>
        <Link to="/properties" className="nav-link">All Properties</Link>
      </div>
    </nav>
  );
};

export default Navbar;
