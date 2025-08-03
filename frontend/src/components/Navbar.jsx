import React from "react";
import { Box, IconButton, Link } from "@mui/material";
import Colors from "../utils/Colors";
import AppRoutes from "../utils/AppRoutes";
import { AccountBox, AccountCircle, Search, VerifiedUser } from "@mui/icons-material";
import AssetHelper from "../utils/AssetHelper";
import HomeAccountMenu from "./HomeAccountMenu";
import Row from "./Row";

const Navbar = () => {
  const [ anchorEl, setAnchorEl ] = React.useState(null); 

  const navBarStyle = { 
    position: "fixed", 
    top: 0, 
    left: 0, 
    width: "100%",  
    height: "50px",
    zIndex: 1000,
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    background: `linear-gradient(90deg, ${Colors.background} 70%)`,
    boxShadow: `0 2px 8px rgba(255, 255, 255, 0.07)` 
  };

  const handleMenuOnOpen = (event) => {
    console.log("clicked " + event.target);
    setAnchorEl(event.target);
  }

  <HomeAccountMenu anchorEl={ anchorEl } setAnchorEl={ setAnchorEl } />

  return (

    <>
      <nav style={ navBarStyle } >
        <Link 
          href={AppRoutes.home}
          style={{ marginRight: 8, display: "flex", justifyContent: "center", alignItems: "center" }}
        >
          <img src={AssetHelper.appIcon} alt="app-logo" style={{ height: "25px", margin: 5 }} />
          StayEn.Casa
        </Link>

        <Row justifyContent="end" >
          <div >
            {/* <Link href={AppRoutes.bookingPage}>Booking</Link>
            {" "}|{" "}
            <Link href={AppRoutes.myBookings}>My Booking</Link> */}

            <Search>

            </Search>
          </div>

          <IconButton onClick={ handleMenuOnOpen } >
            <AccountCircle />
          </IconButton>
        </Row>
      </nav>

      {/* 
          @Utkarsh-1709
          All the menu items are shifted here, 
          do the necessary changes

          >>>>>> Your code that is yet to be shifted

          <Link to="/">Booking</Link> |{" "}
          <Link to="/my-bookings">My Bookings</Link> |{" "}
          <Link to="/properties">All Properties</Link>

          <<<<<<

          afterward delete the comment
      */}
      <HomeAccountMenu 
        anchorEl={ anchorEl } 
        setAnchorEl={ setAnchorEl } 
      />
    </>
  );
};

export default Navbar;
