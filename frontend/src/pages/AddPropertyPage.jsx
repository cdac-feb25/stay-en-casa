import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SupabaseHelper from "../services/SupabaseHelper";
import { createProperty, updatePropertyImages } from "../services/propertyService";
import Row from "../components/Row";
import SizedBox from "../components/SizedBox";
import CustomButton from "../components/CustomButton";
import UserContext from "../utils/UserContext";
import logo from "../assets/stay_en_casa-nobg.png";
import Container from "../components/Container";
import Colors from "../utils/Colors";
import AppRoutes from "../utils/AppRoutes";

const AddPropertyPage = () => {
  // Form state for property fields
  const [form, setForm] = useState({
    propertyName: "",
    propertyDescription: "",
    listingType: "", // Default
    propertyCategory: "", // Default
    price: "",
    area: "",
    unit: "", // Default
    bedrooms: "",
    bathrooms: "",
    floorNumber: "",
    totalFloors: "",
    furnishing: "", // Default
    amenities: "",
    latitude: "",
    longitude: "",
    address: "",
    locality: "",
    city: "",
    state: "",
    country: "",
    pincode: "",
    isAvailable: true,
  });

  const [images, setImages] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  // Handle text input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: name === "isAvailable" ? value === "true" : value,
    });
  };

  // Handle file input change
  const handleImageChange = (e) => {
    const files = Array.from(e.target.files);
    if (files.length > 3) {
      alert("You can upload a maximum of 3 images.");
      return;
    }
    setImages(files);
  };

  // Form submission handler
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      // Ensure user is logged in
      const userId = UserContext.getLoggedInUser().uid;
      if (!userId) {
        setError("You must be logged in to add a property.");
        setLoading(false);
        return;
      }

      // Build property payload
       const propertyPayload = {
        propertyName: form.propertyName,
        propertyDescription: form.propertyDescription,
        listingType: form.listingType,
        propertyCategory: form.propertyCategory,
        price: Number(form.price),
        area: Number(form.area),
        unit: form.unit,
        bedrooms: Number(form.bedrooms),
        bathrooms: Number(form.bathrooms),
        floorNumber: Number(form.floorNumber),
        totalFloors: Number(form.totalFloors),
        furnishing: form.furnishing,
        amenities: form.amenities.split(",").map((a) => a.trim()),
        ownerId: userId,
        isAvailable: form.isAvailable,
        location: {
          latitude: form.latitude,
          longitude: form.longitude,
          address: form.address,
          locality: form.locality,
          city: form.city,
          state: form.state,
          country: form.country,
          pincode: Number(form.pincode),
        },
        images: [],
      };

    useEffect(() => {
        if (form.propertyCategory === "PLOT") {
            setForm((prev) => ({
            ...prev,
            bedrooms: "",
            bathrooms: "",
            floorNumber: "",
            totalFloors: "",
            furnishing: "UNFURNISHED",
            amenities: "",
        }));
    }
}, [form.propertyCategory]);

      // Call backend to create property
      const response = await createProperty(propertyPayload);

      // Response expected from backend: { message, propertyId }
      const propertyId = response.propertyId;

      // Upload images to Supabase
      let imageUrls = [];
      for (let file of images) {
        const publicUrl = await SupabaseHelper.uploadPropertiesPhotoFile(file,propertyId);
        if (publicUrl) imageUrls.push(publicUrl);
      }

      // Update property in backend with image URLs
      if (imageUrls.length > 0) {
        await updatePropertyImages(propertyId, imageUrls);
      }

      // Show backend custom success message
      alert(response.message);
      navigate(AppRoutes.properties);       //Update this my-properties page
    } catch (err) {
      // Show backend custom error message if available
      const backendMsg = err.response?.data?.message || err.message || "Failed to add property.";
      setError(backendMsg);
    } finally {
      setLoading(false);
    }
  };

  const inputStyle = {
    padding: "12px 16px",
    border: "2px solid #e1e5e9",
    borderRadius: "8px",
    fontSize: "14px",
    outline: "none",
    transition: "border-color 0.2s ease",
    fontFamily: "inherit",
  };

  const focusStyle = {
    borderColor: "rgb(226,117,45)",
    boxShadow: "0 0 0 3px rgba(0, 123, 255, 0.1)"
  };

  const fieldsetStyle = {
    border: "2px solid rgb(226,117,45)",
    padding: 24,
    borderRadius: 16,
    marginBottom: 24,
  };

  const legendStyle = {
    fontSize: "16px",
    fontWeight: "600",
    color: "#f9f9f9ff",
    padding: "0 12px",
    backgroundColor: "rgb(226,117,45)",
    borderRadius: "6px",
  };

  return (
    <Container maxWidth = {1100} >
        
        <h2 style={{
            display: "flex",
            alignItems: "center",
            gap: "20px",
            justifyContent: "center",
            color: Colors.textOrange,
            }}>
            <img src={logo} alt="Logo" style={{ height: "60px", width: "auto" }} />
            <span>Add New Property</span>
            <img src={logo} alt="Logo" style={{ height: "60px", width: "auto" }} />
        </h2>
        
        <div style={{ width: "100%" }}>
          {/* Property Details */}
          <fieldset style={fieldsetStyle}>
            <legend style={legendStyle}>Property Details</legend>
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <input
                type="text"
                name="propertyName"
                placeholder="Property Name"
                value={form.propertyName}
                onChange={handleChange}
                required
                style={{ ...inputStyle, flex: 1, minWidth: 250, boxSizing: "border-box" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <select
                name="listingType"
                value={form.listingType}
                onChange={handleChange}
                required
                style={{ ...inputStyle, flex: 1, minWidth: 250, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              >
                <option value="">Select Listing Type</option>
                <option value="RENT">Rent</option>
                <option value="SALE">Sale</option>
              </select>
              <select
                name="propertyCategory"
                value={form.propertyCategory}
                onChange={handleChange}
                required
                style={{ ...inputStyle, flex: 1, minWidth: 250, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              >
                <option value="">Select Category</option>
                <option value="FLAT">Flat</option>
                <option value="VILLA">Villa</option>
                <option value="PLOT">Plot</option>
                <option value="COMMERCIAL">Commercial</option>
              </select>
            </Row>
            <SizedBox height={16} />
            <textarea
              name="propertyDescription"
              placeholder="Property Description"
              value={form.propertyDescription}
              onChange={handleChange}
              rows="4"
              style={{ 
                ...inputStyle,
                width: "100%", 
                resize: "vertical",
                minHeight: "100px",
                boxSizing: "border-box"
              }}
              onFocus={(e) => Object.assign(e.target.style, focusStyle)}
              onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
            />
          </fieldset>

          {/* Pricing & Specifications */}
          <fieldset style={fieldsetStyle}>
            <legend style={legendStyle}>Pricing & Specifications</legend>
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <input 
                type="number" 
                name="price" 
                placeholder="Price (₹)" 
                value={form.price} 
                onChange={handleChange} 
                required 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="number" 
                name="area" 
                placeholder="Area" 
                value={form.area} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 2, minWidth: 100, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <select 
                name="unit" 
                value={form.unit} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 1, minWidth: 120, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              >
                <option value="">Select Unit</option>
                <option value="SQ_FT">Sq.Ft.</option>
                <option value="SQ_YARD">Sq.Yard</option>
                <option value="SQ_M">Sq.M.</option>
                <option value="HECTARE">Hectare</option>
                <option value="ACRE">Acre</option>
              </select>
            </Row>
            <SizedBox height={16} />
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <input 
                type="number" 
                name="bedrooms" 
                placeholder="Bedrooms" 
                value={form.bedrooms} 
                onChange={handleChange}
                disabled={form.propertyCategory === "PLOT"}
                required={form.propertyCategory !== "PLOT"} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="number" 
                name="bathrooms" 
                placeholder="Bathrooms" 
                value={form.bathrooms} 
                onChange={handleChange}
                disabled={form.propertyCategory === "PLOT"}
                required={form.propertyCategory !== "PLOT"} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="number" 
                name="floorNumber" 
                placeholder="Floor Number" 
                value={form.floorNumber} 
                onChange={handleChange}
                disabled={form.propertyCategory === "PLOT"}
                required={form.propertyCategory !== "PLOT"} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="number" 
                name="totalFloors" 
                placeholder="Total Floors" 
                value={form.totalFloors} 
                onChange={handleChange}
                disabled={form.propertyCategory === "PLOT"}
                required={form.propertyCategory !== "PLOT"} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
            </Row>
            <SizedBox height={16} />
            <Row style={{ gap: 16, flexWrap: "wrap", alignItems: "flex-end" }}>
              <select 
                name="furnishing" 
                value={form.furnishing} 
                onChange={handleChange}
                disabled={form.propertyCategory === "PLOT"}
                required={form.propertyCategory !== "PLOT"} 
                style={{ ...inputStyle, flex: 1, minWidth: 200, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              >
                <option value="">Select Furnishing</option>
                <option value="FURNISHED">Fully Furnished</option>
                <option value="SEMI_FURNISHED">Semi Furnished</option>
                <option value="UNFURNISHED">Unfurnished</option>
              </select>
              <input
                type="text"
                name="amenities"
                placeholder="Amenities (comma separated)"
                value={form.amenities}
                onChange={handleChange}
                disabled={form.propertyCategory === "PLOT"}
                required={form.propertyCategory !== "PLOT"}
                style={{ ...inputStyle, flex: 2, minWidth: 300, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
            </Row>
          </fieldset>

          {/* Location */}
          <fieldset style={fieldsetStyle}>
            <legend style={legendStyle}>Location</legend>
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <input 
                type="text" 
                name="address" 
                placeholder="Address" 
                value={form.address} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 2, minWidth: 300, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="text" 
                name="locality" 
                placeholder="Locality" 
                value={form.locality} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 1, minWidth: 200, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
            </Row>
            <SizedBox height={16} />
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <input 
                type="text" 
                name="city" 
                placeholder="City" 
                value={form.city} 
                onChange={handleChange} 
                required 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="text" 
                name="state" 
                placeholder="State" 
                value={form.state} 
                onChange={handleChange} 
                required 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="text" 
                name="country" 
                placeholder="Country" 
                value={form.country} 
                onChange={handleChange} 
                required 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="number" 
                name="pincode" 
                placeholder="Pincode" 
                value={form.pincode} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
            </Row>
            <SizedBox height={16} />
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <input 
                type="number" 
                step="any"
                name="latitude" 
                placeholder="Latitude" 
                value={form.latitude} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
              <input 
                type="number" 
                step="any"
                name="longitude" 
                placeholder="Longitude" 
                value={form.longitude} 
                onChange={handleChange} 
                style={{ ...inputStyle, flex: 1, minWidth: 150, boxSizing: "border-box", marginLeft: "10px" }}
                onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              />
            </Row>
          </fieldset>

            {/* Availability */}
            <fieldset style={fieldsetStyle}>
            <legend style={legendStyle}>Availability</legend>
            <Row style={{ gap: 16, flexWrap: "wrap" }}>
              <select
                  name="isAvailable"
                  value={form.isAvailable ? "true" : "false"}
                  onChange={handleChange}
                  style={{ ...inputStyle, flex: 1, minWidth: 200}}
                  onFocus={(e) => Object.assign(e.target.style, focusStyle)}
                  onBlur={(e) => Object.assign(e.target.style, { borderColor: "#e1e5e9", boxShadow: "none" })}
              >
                <option value="true">Available</option>
                <option value="false">Not Available</option>
              </select>
            </Row>
          </fieldset>       

          {/* Image Upload */}
          <fieldset style={fieldsetStyle}>
            <legend style={legendStyle}>Upload Images</legend>
            <div style={{
              border: "2px dashed #ccc",
              borderRadius: "12px",
              padding: "24px",
              textAlign: "center",
              marginBottom: "16px"
            }}>
              <label style={{
                cursor: "pointer",
                color: "#007bff",
                fontWeight: "500",
                fontSize: "16px"
              }}>
                📸 Click to upload images
                <input
                  type="file"
                  multiple
                  accept="image/*"
                  onChange={handleImageChange}
                  style={{ display: "none" }}
                />
              </label>
              <p style={{ margin: "8px 0 0 0", color: "#666", fontSize: "14px" }}>
                Maximum 3 images allowed
              </p>
            </div>
            
            {images.length > 0 && (
            <Row style={{ flexWrap: "wrap" }}>
                {images.map((img, i) => (
                <div 
                    key={i} 
                    style={{ 
                    position: "relative",
                    width: 120,
                    height: 120,
                    borderRadius: 12,
                    overflow: "hidden", // keeps button inside
                    border: "2px solid #e1e5e9",
                    boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
                    marginLeft: "16px",
                    }}
                >
                    <img
                    src={URL.createObjectURL(img)}
                    alt="preview"
                    style={{
                        width: "100%",
                        height: "100%",
                        objectFit: "cover",
                    }}
                    />
                    <button
                    type="button"
                    onClick={() => setImages(prev => prev.filter((_, idx) => idx !== i))}
                    style={{
                        position: "absolute",
                        top: -5,
                        right: -5,
                        width: 36,
                        height: 36,
                        borderRadius: "50%",
                        border: "none",
                        color: "#E94F64",
                        backgroundColor: 'transparent',
                        fontSize: 20,
                        fontWeight: "bold",
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        padding: 0,
                        lineHeight: 1,
                        cursor: "pointer",
                        zIndex: 10
                    }}
                    >
                    X
                    </button>
                </div>
                ))}
            </Row>
            )}
          </fieldset>

          <CustomButton
            title="Add Property"
            onClick={handleSubmit}
            disabled={loading}
            style={{ 
              width: "100%",
              backgroundColor: loading ? "#6c757d" : "#28a745",
              cursor: loading ? 'not-allowed' : 'pointer',
              opacity: loading ? 0.8 : 1
            }}
          >
            {loading ? "Adding Property..." : "Add Property"}
          </CustomButton>

          {error && (
            <>
              <SizedBox height={16} />
              <Row justify="center">
                <div style={{
                  backgroundColor: "#f8d7da",
                  color: "#721c24",
                  padding: "12px 20px",
                  borderRadius: "8px",
                  border: "1px solid #f5c6cb",
                  textAlign: "center",
                  fontWeight: "500"
                }}>
                  {error}
                </div>
              </Row>
            </>
          )}
        </div>
    </Container>
  );
};

export default AddPropertyPage;