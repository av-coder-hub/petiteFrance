import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "../styles/DistrictDetailPage.css";

const DistrictDetailPage = () => {
  const { districtName } = useParams();
  const [districtDetails, setDistrictDetails] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Fetch district data from backend based on district name
    fetch(`http://localhost:8080/districts/${districtName}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch district data");
        }
        return response.json();
      })
      .then((data) => {
        setDistrictDetails(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching district data:", error);
        setLoading(false);
      });
  }, [districtName]);  // Re-fetch data when districtName changes

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!districtDetails) {
    return <div>District details not found.</div>;
  }

  return (
    <div className="district-detail-page">
      <div className="district-banner">
        <img
          src={districtDetails.bannerImage}  
          alt={districtDetails.name}
          className="district-banner-image"
        />
        <h1>{districtDetails.name}</h1>
        <p>{districtDetails.tagline}</p>
      </div>

      <div className="district-info">
        <h2>About {districtDetails.name}</h2>
        <p>{districtDetails.aboutText}</p>
        <img
          src={districtDetails.mapImage}  // Path from the public folder
          alt={`${districtDetails.name} map`}
          className="district-map-image"
        />
      </div>

      <div className="destinations-section">
        <h2>Popular Destinations in {districtDetails.name}</h2>
        <div className="destinations-grid">
  {districtDetails.destinations.map((destination) => (
    <div key={destination.name} className="destination-card">
      <img
        src={destination.imageUrl}
        alt={destination.name}
        className="destination-image"
      />

      {/* Name, Location (Left) */}
      <div className="destination-info">
        <div className="destination-info-left">
          <h3>{destination.name}</h3>
          <div className="destination-location">
            <span className="icon-location">📍</span>
            <span>{destination.location}</span>
          </div>
        </div>

        {/* Temperature and Category (Right) */}
        <div className="destination-info-right">
          <div className="destination-temperature">
            <span className="icon-temperature">🌡️</span>
            <span>{destination.temperature}°C</span>
          </div>
          <div className="destination-category">
            <span className="icon-category">🏷️</span>
            <span>{destination.category}</span>
          </div>
        </div>
      </div>

      {/* Tagline and Description (Hidden on Hover) */}
      <div className="destination-hover-info">
        <h4>{destination.tagline}</h4>
        <p>{destination.description}</p>
      </div>
    </div>
  ))}
</div>

      </div>
    </div>
  );
};

export default DistrictDetailPage;
