import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { updateProperty, updatePartialProperty, getMyProperties } from "../services/propertyService";
import CustomButton from "../components/CustomButton";
import Container from "../components/Container";
import Row from "../components/Row";
import SizedBox from "../components/SizedBox";
import UserContext from "../utils/UserContext";

const EditPropertyPage = () => {
  const { propertyId } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({});
  const [loading, setLoading] = useState(false);
  const [partialUpdate, setPartialUpdate] = useState(false);

  useEffect(() => {
    const fetchProperty = async () => {
      try {
        const ownerId = UserContext.getLoggedInUser().id;
        const data = await getMyProperties(ownerId);
        const property = data.find((p) => p.propertyId === propertyId);
        if (!property) {
          alert("Property not found or you don't own this property.");
          navigate(AppRoutes.myProperties);
        } else {
          setForm(property);
        }
      } catch {
        alert("Failed to fetch property details.");
        navigate(AppRoutes.myProperties);
      }
    };
    fetchProperty();
  }, [propertyId, navigate]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    setLoading(true);
    try {
      if (partialUpdate) {
        await updatePartialProperty(propertyId, form);
        alert("Property partially updated!");
      } else {
        await updateProperty(propertyId, form);
        alert("Property fully updated!");
      }
      navigate(AppRoutes.myProperties);
    } catch {
      alert("Failed to update property.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container style={{ marginTop: 40, maxWidth: 800 }}>
      <h2>Edit Property</h2>
      <label>
        <input
          type="checkbox"
          checked={partialUpdate}
          onChange={(e) => setPartialUpdate(e.target.checked)}
        />{" "}
        Enable Partial Update
      </label>
      <SizedBox height={20} />
      <form>
        <Row style={{ flexDirection: "column", gap: 12 }}>
          <input
            type="text"
            name="propertyName"
            placeholder="Property Name"
            value={form.propertyName || ""}
            onChange={handleChange}
          />
          <textarea
            name="propertyDescription"
            placeholder="Property Description"
            value={form.propertyDescription || ""}
            onChange={handleChange}
          />
          <input
            type="number"
            name="price"
            placeholder="Price"
            value={form.price || ""}
            onChange={handleChange}
          />
        </Row>
        <SizedBox height={20} />
        <CustomButton onPress={handleSubmit} disabled={loading} title="Save Changes">
          {loading ? "Saving..." : "Save Changes"}
        </CustomButton>
      </form>
    </Container>
  );
};

export default EditPropertyPage;
