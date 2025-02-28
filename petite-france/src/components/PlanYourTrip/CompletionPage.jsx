import React from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/CompletionPage.css";

const CompletionPage = ({ selectedDestinations, tripStartDate, tripEndDate }) => {
  const navigate = useNavigate();

  // Handle Plan Another Trip button click
  const handlePlanAnotherTrip = () => {
    navigate("/plan-your-trip"); // Redirect to the Plan Your Trip page
  };

  return (
    <div className="completion-page">
      <h1>🎉 Trip Completed! 🎉</h1>
      <p>We hope you had an amazing experience exploring these beautiful places!</p>

      <div className="trip-details">
        <h2>🗓 Trip Summary</h2>
        <p>
          <strong>Start Date:</strong> {tripStartDate ? tripStartDate.toDateString() : "N/A"}
        </p>
        <p>
          <strong>End Date:</strong> {tripEndDate ? tripEndDate.toDateString() : "N/A"}
        </p>
        <p>
          <strong>Total Destinations Visited:</strong> {selectedDestinations.length}
        </p>
      </div>

      {selectedDestinations.length > 0 ? (
        <div className="destination-list">
          <h3>📍 Visited Destinations:</h3>
          <ul>
            {selectedDestinations.map((destination, index) => (
              <li key={index} className="destination-item">
                <strong>{destination.name}</strong> ({destination.district})
              </li>
            ))}
          </ul>
        </div>
      ) : (
        <p className="no-destinations">No destinations were visited. Plan a new trip and explore!</p>
      )}

      <div className="navigation-buttons">
        <button className="restart-btn" onClick={handlePlanAnotherTrip}>
          Plan Another Trip
        </button>
      </div>

      <div className="completion-message">
        🌟 Thank you for traveling with us. We look forward to helping you plan your next adventure!
      </div>
    </div>
  );
};

export default CompletionPage;
