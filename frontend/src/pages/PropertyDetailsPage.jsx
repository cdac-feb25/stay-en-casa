import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getPropertyDetailsById } from "../services/propertyService"; 
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton";
import AppRoutes from "../utils/AppRoutes";
import PropertyImages from "../components/PropertyImages";
import Colors from "../utils/Colors";
import UserContext from "../utils/UserContext";
import RedirectionHelper from "../services/RedirectionHelper";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import ImageCarousel from "../components/ImageCarousel";

import {
  Box,
  Tabs,
  Tab,
  Typography,
  Grid,
  Paper,
  Stack,
  SvgIcon,
  Card,
  CardContent,
  Divider,
} from "@mui/material";

import {
  Bed as BedIcon,
  Bathtub as BathIcon,
  SquareFoot as AreaIcon,
  Home as HomeIcon,
  Wifi as WifiIcon,
  LocalParking as ParkingIcon,
  Elevator as ElevatorIcon,
  Yard as GardenIcon,
  FitnessCenter as GymIcon,
  Spa as SpaIcon,
  Pool as PoolIcon,
  OutdoorGrill as BBQIcon,
  SportsBasketball as PlaygroundIcon,
  LocalBar as BarIcon,
  Weekend as LoungeIcon,
} from "@mui/icons-material";

// Icon mapper for known amenities and fields
const iconMap = {
  Bedrooms: <BedIcon />,
  Bathrooms: <BathIcon />,
  Area: <AreaIcon />,
  Category: <HomeIcon />,
  "Free Wifi": <WifiIcon />,
  Parking: <ParkingIcon />,
  Lift: <ElevatorIcon />,
  Elevator: <ElevatorIcon />,
  Garden: <GardenIcon />,
  Gym: <GymIcon />,
  Spa: <SpaIcon />,
  Pool: <PoolIcon />,
  "BBQ Area": <BBQIcon />,
  Playground: <PlaygroundIcon />,
  Bar: <BarIcon />,
  Lounge: <LoungeIcon />,
};


const PropertyDetailsPage = () => {
  const { propertyId } = useParams(); // propertyId from route

  RedirectionHelper.fromSpecificPropertyPage(propertyId);

  /** @type { [ Property, Function ] } */
  const [ property, setProperty ] = useState(null);
  const [ error, setError ] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProperty = async () => {
      try {
        let propertyById = LocalStorageHelper.getPropertyById(propertyId);

        if(!propertyById) {
          propertyById = await getPropertyDetailsById(propertyId);
        }

        setProperty(propertyById);
      } 
      catch (err) {
        setError(err.message || "Failed to load property details.");
      }
    };

    fetchProperty();
  }, [propertyId]);

  const handleBooking = () => {
    const user = UserContext.getLoggedInUser();
    if (user && user.uid) {
      // Redirect to BookingPage with propertyId
      // navigate(AppRoutes.bookingPage, { state: { propertyId } });
      navigate(`/booking-page/${property.propertyId}`);
    } else {
      // Redirect to LoginPage
      navigate(AppRoutes.login, { state: { from: AppRoutes.showPropertyById_param.replace(":propertyId", propertyId) } });
    }
  };

  if (error) {
    return (
      <Container>
        <Column align="center">
          <h2 style={{ color: "red" }}>{error}</h2>
        </Column>
      </Container>
    );
  }

  if (!property) {
    return (
      <Container>
        <Column align="center">
        <h2 style={{color: Colors.textOrange }}>Loading property details...</h2>
        </Column>
      </Container>
    );
  }

  const legendStyle = {
    fontSize: "16px",
    fontWeight: "600",
    color: "#f9f9f9ff",
    padding: "0 12px",
    backgroundColor: "rgb(226,117,45)",
    borderRadius: "6px",
  };

  const fieldsetStyle = {
    border: "1px solid rgb(226,117,45)",
    borderRadius: "8px",
    padding: "20px",
    color: "white",
    textAlign: "left",
    marginBottom: "24px",
  }

//   console.log("Property Details: ", property);

  return (
    <>
        <ImageCarousel images={ property.images } />

        <Container maxWidth = {"auto"} >
        <div style={{
          display: "grid",
          gridTemplateColumns: "1fr 1fr",
          gap: "32px",
          alignItems: "start",
        }}
        >
        {/* Left Column - Details */}
        <div>
          <h2 style={{ marginBottom: "20px", color: "orange" }}>
            {property.propertyName}
          </h2>

          <Row justifyContent="space-between" >
            {/* Details Section */}
            <div style={{ marginBottom: "24px",  color: "white", textAlign: "left" }}>
              <fieldset style={ fieldsetStyle }>
              <legend style={ legendStyle }>Details</legend>
                  <p><b>Description: </b> {property.propertyDescription}</p>
                  <p><b>Type: </b> {property.listingType
                  ? property.listingType.charAt(0).toUpperCase() + property.listingType.slice(1).toLowerCase() : "N/A"}
                  </p>
                  <p><b>Category: </b>
                  {property.propertyCategory 
                  ? property.propertyCategory.charAt(0).toUpperCase() + property.propertyCategory.slice(1).toLowerCase() : "N/A"}
                  </p>
                  <p><b>Price: </b> ₹{property.price.toLocaleString()}</p>
                  <p><b>Area: </b> {property.area} {property.unit}</p>
                  <p><b>Bedrooms: </b> {property.bedrooms}</p>
                  <p><b>Bathrooms: </b> {property.bathrooms}</p>
                  <p><b>Furnishing: </b> {property.furnishing}</p>
              </fieldset>

              {/* Location Section */}
              <div style={{ marginBottom: "24px", color: "white", textAlign: "left" }}>
                <fieldset style={ fieldsetStyle }>
                <legend style={ legendStyle }>Location</legend>
                <p><b>Address: </b>{property.location?.address}, {property.location?.locality}</p>
                <p>{property.location?.city}, {property.location?.state}, {property.location?.country}</p>
                <p><b>Pincode:</b> {property.location?.pincode}</p>
                </fieldset>
              </div>
            </div>

            
          </Row>

          <CustomButton
            title="Back to Properties"
            onPress={() => navigate(AppRoutes.showAllProperties)}
            bgColor="#f5f5f5"
            textColor="black"
            width="220px"
            height="48px"
          />
          <CustomButton
            title="Book this Property"
            onPress={ handleBooking }
            bgColor="#f5f5f5"
            textColor="black"
            width="220px"
            height="48px"
          />
        </div>

      </div>
    </Container>
  </>
  );
};



export default PropertyDetailsPage;

