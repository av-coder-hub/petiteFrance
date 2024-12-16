import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import '../styles/DestinationDetails.css';

const DestinationDetails = () => {
  const { Name } = useParams();
  const [destination, setDestination] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null); // State for handling errors

  useEffect(() => {
    const fetchDestination = async () => {
      try {
        const url = `http://localhost:8080/districts/${Name.toLowerCase()}`; // Correct URL
        console.log(`Fetching: ${url}`);
        
        const response = await fetch(url, {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        });
  
        if (!response.ok) {
          throw new Error(`HTTP Error ${response.status}: ${response.statusText}`);
        }
  
        const data = await response.json();
        setDestination(data);
        setError(null);
      } catch (err) {
        console.error('Fetch error:', err);
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
  
    fetchDestination();
  }, [Name]);
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!destination) return <div>Destination not found</div>;

  return (
    <div className="destination-details">
      <img src={destination.img} alt={destination.name} />
      <h1>{destination.name}</h1>
      <p>{destination.description}</p>
    </div>
  );
};

export default DestinationDetails;
