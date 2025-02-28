import React from "react";
import PropTypes from "prop-types";

const TripSummary = ({ plan, onVisitChange }) => {
  // Calculate the total number of destinations and the visited ones
  const totalDestinations = plan.reduce((acc, day) => acc + day.destinations.length, 0);
  const visitedDestinations = plan.reduce(
    (acc, day) =>
      acc + day.destinations.filter((destination) => destination.visited).length,
    0
  );
  const progressPercentage = totalDestinations > 0 ? (visitedDestinations / totalDestinations) * 100 : 0;

  return (
    <div className="trip-summary">
      <h2>Your Itinerary</h2>
      {plan.length === 0 ? (
        <p>No destinations selected. Please select destinations to create a plan.</p>
      ) : (
        <>
          {/* Progress Bar */}
          <div className="progress-bar-container">
            <div className="progress-bar-background">
              <div
                className="progress-bar"
                style={{ width: `${progressPercentage}%` }}
              ></div>
            </div>
            <p className="progress-text">{`${Math.round(progressPercentage)}% Completed`}</p>
          </div>

          {/* Itinerary Details */}
          {plan.map((day, dayIndex) => (
            <div key={dayIndex} className="day-summary">
              <h3>Day {day.day}</h3>
              <ul className="destination-list">
                {day.destinations.map((destination, destinationIndex) => (
                  <li key={destinationIndex} className="destination-item">
                    <input
                      type="checkbox"
                      checked={destination.visited || false}
                      onChange={() => onVisitChange(dayIndex, destinationIndex)}
                    />
                    <span className="destination-name">{destination.name}</span>
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </>
      )}
    </div>
  );
};

TripSummary.propTypes = {
  plan: PropTypes.array.isRequired,
  onVisitChange: PropTypes.func.isRequired,
};

export default TripSummary;




// import React, { useState, useEffect } from "react";
// import "../../styles/TripSummary.css";

// const TripSummary = ({ selectedDestinations = [], totalDays = 1 }) => {
//   const [completionStatus, setCompletionStatus] = useState(
//     selectedDestinations.reduce((acc, dest) => {
//       acc[dest.name] = false;
//       return acc;
//     }, {})
//   );

//   useEffect(() => {
//     const newCompletionStatus = selectedDestinations.reduce((acc, dest) => {
//       acc[dest.name] = false; // Default to 'false' (not completed)
//       return acc;
//     }, {});
  
//     // Update state only if there's a change to prevent infinite loop
//     if (JSON.stringify(newCompletionStatus) !== JSON.stringify(completionStatus)) {
//       setCompletionStatus(newCompletionStatus);
//     }
//   }, [selectedDestinations]); // Run only when selectedDestinations changes
  

//   const toggleCompletion = (destinationName) => {
//     setCompletionStatus((prevStatus) => ({
//       ...prevStatus,
//       [destinationName]: !prevStatus[destinationName],
//     }));
//   };

//   const calculateDayProgress = (dayDestinations) => {
//     const completedCount = dayDestinations.filter(
//       (dest) => completionStatus[dest.name]
//     ).length;
//     return dayDestinations.length > 0
//       ? Math.round((completedCount / dayDestinations.length) * 100)
//       : 0;
//   };

//   if (!selectedDestinations || selectedDestinations.length === 0) {
//     return <div>No destinations selected. Please create your itinerary.</div>;
//   }

//   return (
//     <div className="trip-summary">
//       <h2>Your Itinerary</h2>
//       <div className="days-container">
//         {Array.from({ length: totalDays }, (_, index) => {
//           const day = index + 1;
//           const dayDestinations = selectedDestinations.slice(
//             Math.floor((day - 1) * selectedDestinations.length / totalDays),
//             Math.floor(day * selectedDestinations.length / totalDays)
//           );

//           const progress = calculateDayProgress(dayDestinations);

//           return (
//             <div key={day} className="day-summary">
//               <h3>Day {day}</h3>
//               {dayDestinations.length === 0 ? (
//                 <p>No destinations assigned for this day.</p>
//               ) : (
//                 <ul>
//                   {dayDestinations.map((dest) => (
//                     <li key={dest.name} className="destination-summary">
//                       <span>{dest.name}</span>
//                       <input
//                         type="checkbox"
//                         checked={completionStatus[dest.name]}
//                         onChange={() => toggleCompletion(dest.name)}
//                       />
//                       <label>
//                         {completionStatus[dest.name] ? "Visited" : "Not Visited"}
//                       </label>
//                     </li>
//                   ))}
//                 </ul>
//               )}
//               <div className="progress-bar-container">
//                 <div
//                   className="progress-bar"
//                   style={{ width: `${progress}%` }}
//                 ></div>
//               </div>
//               <span>Progress: {progress}%</span>
//             </div>
//           );
//         })}
//       </div>
//     </div>
//   );
// };

// export default TripSummary;
