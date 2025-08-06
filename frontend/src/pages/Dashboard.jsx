// import { Button, Drawer } from "@mui/material";
// import ApiCaller from "../services/ApiCaller";
// import RedirectionHelper from "../services/RedirectionHelper";
// import Column from "../components/Column";

// import React, { useEffect, useState } from 'react';
// import { Container, Box, Typography, Button, CircularProgress } from '@mui/material';
// import getLocation from "../services/LocationService";


// function Dashboard() {
//     RedirectionHelper.fromHome();
    
//     return (
//         <div>
//             <h1>Dashboard Page (After Login — Buyer, Seller, or Admin)</h1>
//             <h3>CallToAction btn [Add new property, Edit/Delete existing properties, Saved properties / favorites]</h3>

//             <Column>

//                 <Button sx={{ mb:2 }} onClick={ () => { ApiCaller.refreshToken(); } }>
//                     Refresh token
//                 </Button>

//             </Column>
//         </div>
//     );
// }

// export default Dashboard;
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import DashboardSearch from "../components/DashboardSearch";
import Container from "../components/Container";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton";
import SizedBox from "../components/SizedBox";
import Colors from "../utils/Colors";
import UserContext from "../utils/UserContext";
import { getMyProperties } from "../services/propertyService";

const DashboardPage = () => {
  const navigate = useNavigate();
  const [properties, setProperties] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [loading, setLoading] = useState(true);

  const user = UserContext.getLoggedInUser();

  useEffect(() => {
    const fetchProperties = async () => {
      if (!user) {
        alert("Please login first.");
        navigate("/login");
        return;
      }
      try {
        const data = await getMyProperties(user.id);
        setProperties(data);
        setFiltered(data);
      } catch (err) {
        console.error("Error fetching properties:", err);
      } finally {
        setLoading(false);
      }
    };
    fetchProperties();
  }, [navigate, user]);

  // Search logic
  const handleSearch = (query) => {
    if (!query.trim()) {
      setFiltered(properties);
      return;
    }
    const q = query.toLowerCase();
    const results = properties.filter(
      (p) =>
        p.propertyName.toLowerCase().includes(q) ||
        p.location?.city?.toLowerCase().includes(q) ||
        p.location?.locality?.toLowerCase().includes(q)
    );
    setFiltered(results);
  };

  // Stats
  const total = properties.length;
  const available = properties.filter((p) => p.isAvailable).length;
  const unavailable = total - available;

  return (
    <Container maxWidth={1200}>
      <h1 style={{ color: Colors.textOrange, textAlign: "center" }}>
        Welcome back, {user?.name || "User"} 👋
      </h1>

      <SizedBox height={24} />

      {/* Property Stats */}
      <Row style={{ justifyContent: "space-around", marginBottom: "30px" }}>
        <div style={cardStyle}>
          <h3>Total Properties</h3>
          <p>{total}</p>
        </div>
        <div style={cardStyle}>
          <h3>Available</h3>
          <p>{available}</p>
        </div>
        <div style={cardStyle}>
          <h3>Not Available</h3>
          <p>{unavailable}</p>
        </div>
      </Row>

      {/* Search Bar */}
      <DashboardSearch onSearch={handleSearch} />

      {/* Search Results */}
      {loading ? (
        <p style={{ textAlign: "center" }}>Loading properties...</p>
      ) : filtered.length === 0 ? (
        <p style={{ textAlign: "center", color: "gray" }}>
          No properties found.
        </p>
      ) : (
        <Row style={{ flexWrap: "wrap", gap: "20px", justifyContent: "center" }}>
          {filtered.map((p) => (
            <div key={p.propertyId} style={propertyCardStyle}>
              <h3 style={{ color: Colors.textOrange }}>{p.propertyName}</h3>
              <p>
                {p.location?.city}, {p.location?.state}
              </p>
              <p>Price: ₹{p.price}</p>
              <CustomButton
                title="View Details"
                onClick={() => navigate(`/property/${p.propertyId}`)}
              />
            </div>
          ))}
        </Row>
      )}
    </Container>
  );
};

// Card styles
const cardStyle = {
  flex: 1,
  minWidth: "200px",
  background: "#f8f9fa",
  borderRadius: "12px",
  padding: "20px",
  textAlign: "center",
  boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
  marginleft: "10px",
};

const propertyCardStyle = {
  width: "280px",
  background: "#fff",
  borderRadius: "12px",
  padding: "20px",
  boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
  marginleft: "10px",
};

export default DashboardPage;
