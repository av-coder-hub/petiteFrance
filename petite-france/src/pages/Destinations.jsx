import React, { useEffect, useState } from 'react';

const Destinations = () => {
  const [destinations, setDestinations] = useState([]);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    // Fetch data from the backend
    fetch("http://localhost:8080/districts  ")
      .then((response => {
        if (!response.ok) {
          // If the response is not OK, throw an error
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      }))
      .then(data => setDestinations(data))
      .catch(err => {
        console.error("Error fetching destinations:", err);
        setError(err.message);
      });
  }, []);

  if (error) {
    return <div>Error: {error}</div>;
  }

  // Check if destinations is an array before using .map
  if (!Array.isArray(destinations)) {
    return <div>No destinations available</div>;
  }

  return (
    <div>
  
      {destinations.map(destination => (
        <div key={destination.id}>
          <h3>{destination.name}</h3>
          <p>{destination.aboutText}</p>
        </div>
      ))}
    </div>
  );
};

export default Destinations;
