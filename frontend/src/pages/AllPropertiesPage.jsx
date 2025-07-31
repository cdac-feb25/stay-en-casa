import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllProperties } from '../services/propertyService';

/**
 * AllPropertiesPage component
 * 
 * - Fetches and displays all available properties in a tabular format.
 * - Allows users to click on a row to view detailed information 
 *   about a specific property.
 * - Uses React Router for navigation.
 */

const AllPropertiesPage = () => {

  // State to store list of properties
  const [properties, setProperties] = useState([]);

  // State to handle loading UI
  const [loading, setLoading] = useState(true);

  // React Router's navigation hook
  const navigate = useNavigate();

  /**
   * useEffect hook to fetch all properties from backend
   * when the component is mounted.
   */
  
  useEffect(() => {
    const fetchProperties = async () => {
      try {
        const response = await getAllProperties();
        console.log("API Response:", response);
        setProperties(response);    // Save response to state
      } catch (error) {
        console.error("Error fetching properties:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchProperties();
  }, []);

  // Show loader while fetching
  if (loading) return <div>Loading properties...</div>;

  return (
    <div style={{ maxWidth: 900, margin: '0 auto', padding: 24 }}>
      <h2>All Properties</h2>
      {Array.isArray(properties) && properties.length === 0 ? (
        <p>No properties found.</p>
      ) : (
        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr style={{ background: '#f5f5f5' }}>
              <th style={{ padding: 8, border: '1px solid #ddd' }}>Name</th>
              <th style={{ padding: 8, border: '1px solid #ddd' }}>Type</th>
              <th style={{ padding: 8, border: '1px solid #ddd' }}>Category</th>
              <th style={{ padding: 8, border: '1px solid #ddd' }}>Price</th>
              <th style={{ padding: 8, border: '1px solid #ddd' }}>Furnishing</th>
              <th style={{ padding: 8, border: '1px solid #ddd' }}>City</th>
            </tr>
          </thead>
          <tbody>
            {properties.map((property) => (
              <tr
                key={property.propertyId}
                style={{ cursor: 'pointer' }}
                onClick={() => navigate(`/properties/${property.propertyId}`)}
              >
                <td style={{ padding: 8, border: '1px solid #ddd' }}>{property.propertyName}</td>
                <td style={{ padding: 8, border: '1px solid #ddd' }}>{property.listingType}</td>
                <td style={{ padding: 8, border: '1px solid #ddd' }}>{property.propertyCategory}</td>
                <td style={{ padding: 8, border: '1px solid #ddd' }}>₹{property.price.toLocaleString()}</td>
                <td style={{ padding: 8, border: '1px solid #ddd' }}>{property.furnishing}</td>
                <td style={{ padding: 8, border: '1px solid #ddd' }}>{property.location?.city}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AllPropertiesPage;