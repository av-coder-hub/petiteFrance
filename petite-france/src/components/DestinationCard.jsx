// DestinationCard.jsx
import React from 'react';
import '../styles/DestinationCard.css';

const DestinationCard = ({ destination }) => {
    return (
        <div className="destination-card">
            <div className="image-container">
                <img
                    src={destination.imageUrl}
                    alt={destination.name}
                    className="destination-image"
                />
                <div className="destination-hover-description">
                    {destination.description}
                </div>
            </div>
            <div className="destination-info">
                <h4>{destination.name}</h4>
                <p className="location">📍 {destination.location}</p>
                <div className="destination-details">
                    <span className="temperature">🌡️ {destination.temperature}°C</span>
                    <span className="category">📌 {destination.category}</span>
                </div>
            </div>
        </div>
    );
};

export default DestinationCard;
