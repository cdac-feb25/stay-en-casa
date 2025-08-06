import React, { useState } from "react";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton";
import Colors from "../utils/Colors";

const DashboardSearch = ({ onSearch }) => {
  const [query, setQuery] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (query.trim()) onSearch(query);
  };

  return (
    <form onSubmit={handleSubmit} style={{
      display: "flex",
      gap: "10px",
      margin: "20px auto",
      maxWidth: "600px",
    }}>
      <input
        type="text"
        placeholder="Search by property name, city or locality..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        style={{
          flex: 1,
          padding: "12px 16px",
          border: "2px solid #e1e5e9",
          borderRadius: "8px",
          fontSize: "14px",
        }}
      />
      <CustomButton 
        title="Search" 
        type="submit" 
        style={{ backgroundColor: Colors.textOrange }}
      />
    </form>
  );
};

export default DashboardSearch;