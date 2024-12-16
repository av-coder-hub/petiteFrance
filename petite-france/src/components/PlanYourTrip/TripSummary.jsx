import React, { useState } from "react";
import "../../styles/TripSummary.css";

const TripSummary = ({ selectedDestinations, totalDays }) => {
  const [completionStatus, setCompletionStatus] = useState(
    selectedDestinations.reduce((acc, dest) => {
      acc[dest.name] = false; // Default all destinations as 'Not Visited'
      return acc;
    }, {})
  );

  const toggleCompletion = (destinationName) => {
    setCompletionStatus((prevStatus) => ({
      ...prevStatus,
      [destinationName]: !prevStatus[destinationName], // Toggle between Visited and Not Visited
    }));
  };

  const calculateDayProgress = (dayDestinations) => {
    const completedCount = dayDestinations.filter(
      (dest) => completionStatus[dest.name]
    ).length;
    return Math.round((completedCount / dayDestinations.length) * 100);
  };

  return (
    <div className="trip-summary">
      <h2>Your Itinerary</h2>
      <div className="days-container">
        {Array.from({ length: totalDays }, (_, index) => {
          const day = index + 1;
          const dayDestinations = selectedDestinations.slice(
            (day - 1) * Math.ceil(selectedDestinations.length / totalDays),
            day * Math.ceil(selectedDestinations.length / totalDays)
          );

          const progress = calculateDayProgress(dayDestinations);

          return (
            <div key={day} className="day-summary">
              <h3>Day {day}</h3>
              <ul>
                {dayDestinations.map((dest) => (
                  <li key={dest.name} className="destination-summary">
                    <span>{dest.name}</span>
                    <input
                      type="checkbox"
                      checked={completionStatus[dest.name]}
                      onChange={() => toggleCompletion(dest.name)}
                    />
                    <label>{completionStatus[dest.name] ? "Visited" : "Not Visited"}</label>
                  </li>
                ))}
              </ul>
              <div className="progress-bar-container">
                <div
                  className="progress-bar"
                  style={{ width: `${progress}%` }}
                ></div>
              </div>
              <span>Progress: {progress}%</span>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default TripSummary;
