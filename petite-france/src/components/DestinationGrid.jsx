// DestinationGrid.jsx
import React from 'react';
import DestinationCard from './DestinationCard';
import '../styles/DestinationCard.css';

const DestinationGrid = ({ destinations }) => {
    return (
        <div className="destination-grid">
            {destinations.map((destination) => (
                <DestinationCard key={destination.id} destination={destination} />
            ))}
        </div>
    );
};

export default DestinationGrid;
