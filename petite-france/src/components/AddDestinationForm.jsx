import React, { useState } from "react";
import axios from "axios";

const AddDestinationForm = ({ districtId }) => {
  const [destination, setDestination] = useState({
    name: "",
    location: "",
    temperature: "",
    tagline: "",
    description: "",
    imageUrl: "",
    category: ""
  });

  const handleChange = (e) => {
    setDestination({ ...destination, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/districts/${districtId}/addDestination`,
        destination
      );
      alert(response.data);
    } catch (error) {
      console.error("Error adding destination:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" name="name" placeholder="Name" onChange={handleChange} required />
      <input type="text" name="location" placeholder="Location" onChange={handleChange} required />
      <input type="number" name="temperature" placeholder="Temperature" onChange={handleChange} required />
      <input type="text" name="tagline" placeholder="Tagline" onChange={handleChange} required />
      <textarea name="description" placeholder="Description" onChange={handleChange} required></textarea>
      <input type="url" name="imageUrl" placeholder="Image URL" onChange={handleChange} required />
      <input type="text" name="category" placeholder="Category" onChange={handleChange} required />
      <button type="submit">Add Destination</button>
    </form>
  );
};

export default AddDestinationForm;
