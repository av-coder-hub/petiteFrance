import React, { useState, useEffect } from "react";
import { getDestinationsByDistrict } from "../../services/apiService";
import "../../styles/PlanYourTrip.css";
import TripSummary from "./TripSummary";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import CompletionPage from "./CompletionPage"; // Import CompletionPage

const PlanYourTrip = () => {
  const [days, setDays] = useState(1); // Default: 1 day
  const [destinations, setDestinations] = useState([]);
  const [selectedDestinations, setSelectedDestinations] = useState([]);
  const [plan, setPlan] = useState([]);
  const [currentPage, setCurrentPage] = useState('create'); // 'create' or 'summary'
  const [tripStartDate, setTripStartDate] = useState(null);
  const [tripEndDate, setTripEndDate] = useState(null);
  const [tripCompleted, setTripCompleted] = useState(false);

  useEffect(() => {
    const fetchDestinations = async () => {
      const districts = ['Puducherry', 'Mahe', 'Karaikal', 'Yanam'];
      const allDestinations = [];

      for (const district of districts) {
        try {
          const data = await getDestinationsByDistrict(district);
          allDestinations.push({
            district,
            destinations: data?.destinations && Array.isArray(data.destinations)
              ? data.destinations
              : [],
          });
        } catch (error) {
          console.error(`Error fetching destinations for ${district}:`, error);
          allDestinations.push({ district, destinations: [] });
        }
      }

      setDestinations(allDestinations);
    };

    fetchDestinations();
  }, []);

  // Handle destination selection/deselection
  const handleDestinationSelect = (district, destination) => {
    const newSelection = [...selectedDestinations];
    const index = newSelection.findIndex(
      (item) => item.district === district && item.name === destination.name
    );

    if (index === -1) {
      newSelection.push({ district, ...destination });
    } else {
      newSelection.splice(index, 1); // Deselect if already selected
    }

    setSelectedDestinations(newSelection);
  };

  // Create a plan based on selected destinations and number of days
  const handleCreatePlan = () => {
    if (selectedDestinations.length === 0) {
      alert('Please select at least one destination!');
      return;
    }

    if (!tripStartDate || !tripEndDate) {
      alert('Please select start and end dates for your trip!');
      return;
    }

    const plan = [];
    let currentDay = [];
    let currentDistrict = selectedDestinations[0]?.district; // Start with the first district

    // Loop through selected destinations and distribute them into days
    selectedDestinations.forEach((destination, index) => {
      // If the current destination is from the same district as the previous one
      if (destination.district === currentDistrict) {
        currentDay.push(destination);
      } else {
        // If the destination is from a different district, push the current day to the plan
        plan.push({ day: plan.length + 1, destinations: currentDay });
        currentDay = [destination]; // Start a new day with the new district's destination
        currentDistrict = destination.district; // Update to the new district
      }

      // If we already have 4 destinations for the day, move to the next day
      if (currentDay.length === 4) {
        plan.push({ day: plan.length + 1, destinations: currentDay });
        currentDay = []; // Start a new day
      }
    });

    // Add the last day (in case there are remaining destinations)
    if (currentDay.length > 0) {
      plan.push({ day: plan.length + 1, destinations: currentDay });
    }

    setPlan(plan);
    setTripCompleted(true); // Trip is complete after plan creation
    setCurrentPage('summary');
  };

  // Handle calendar date changes and calculate days automatically
  const handleDateChange = (dates) => {
    setTripStartDate(dates[0]);
    setTripEndDate(dates[1]);

    // Automatically calculate the number of days based on the selected dates
    if (dates[0] && dates[1]) {
      const timeDiff = dates[1] - dates[0];
      const dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1; // Calculate days
      setDays(dayDiff);
    }
  };

  return (
    <div className="plan-your-trip">
      <h1>Plan Your Trip</h1>

      {currentPage === 'create' ? (
        <>
          {/* Days Selection */}
          <div className="days-selection">
            <label htmlFor="days">Select Number of Days:</label>
            <input
              type="number"
              id="days"
              min="1"
              max="7"
              value={days}
              onChange={(e) => setDays(Math.min(Math.max(e.target.value, 1), 7))}
              readOnly
            />
          </div>

          {/* Date Selection */}
          <div className="date-selection">
            <label>Select Trip Dates:</label>
            <Calendar
              selectRange={true}
              onChange={handleDateChange}
              value={[tripStartDate, tripEndDate]}
              minDate={new Date()}
              maxDate={new Date(new Date().setDate(new Date().getDate() + 6))} // Max 7 days range
            />
          </div>

          {/* Destinations List */}
          <div className="destinations-list">
            {destinations.map((districtData) => (
              <div key={districtData.district} className="district">
                <h2>{districtData.district}</h2>
                <div className="destination-items">
                  {districtData.destinations.length > 0 ? (
                    districtData.destinations.map((destination) => (
                      <div
                        key={destination.name}
                        className={`destination-item ${
                          selectedDestinations.some(
                            (selected) =>
                              selected.district === districtData.district &&
                              selected.name === destination.name
                          )
                            ? 'selected'
                            : ''
                        }`}
                        onClick={() =>
                          handleDestinationSelect(districtData.district, destination)
                        }
                      >
                        <img
                          src={destination.imageUrl}
                          alt={destination.name}
                          className="destination-image"
                        />
                        <h3>{destination.name}</h3>
                        <p>{destination.category}</p>
                      </div>
                    ))
                  ) : (
                    <p>No destinations available for this district.</p>
                  )}
                </div>
              </div>
            ))}
          </div>

          {/* Next Button */}
          <button className="next-btn" onClick={handleCreatePlan}>
            Create Plan
          </button>
        </>
      ) : (
        <>
          <TripSummary selectedDestinations={selectedDestinations} totalDays={days} />
          {tripCompleted && (
            <CompletionPage
              selectedDestinations={selectedDestinations}
              totalDays={days}
              tripStartDate={tripStartDate}
              tripEndDate={tripEndDate}
              onRestart={() => setTripCompleted(false)} // Reset trip state for restarting
            />
          )}
        </>
      )}
    </div>
  );
};

export default PlanYourTrip;
