import React from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate instead of useHistory
import "../../styles/CompletionPage.css";

const CompletionPage = ({ selectedDestinations, tripStartDate, tripEndDate }) => {
  return (
    <div className="completion-page">
      <h1>Trip Completed!</h1>
      <p>We hope you had an amazing experience!</p>

      <div className="trip-details">
        <h2>Trip Summary</h2>
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

      <div className="destination-list">
        <h3>Visited Destinations:</h3>
        <ul>
          {selectedDestinations.map((destination, index) => (
            <li key={index}>
              <strong>{destination.name}</strong> ({destination.district})
            </li>
          ))}
        </ul>
      </div>

      <div className="completion-message">
        🎉 Feel free to revisit and plan again.
      </div>
    </div>
  );
};

export default CompletionPage;
