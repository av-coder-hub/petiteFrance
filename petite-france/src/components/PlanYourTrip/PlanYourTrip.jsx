import React, { useState, useEffect } from "react";
import { getDestinationsByDistrict } from "../../services/apiService";
import { useNavigate } from "react-router-dom";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import "../../styles/PlanYourTrip.css";
import TripSummary from "./TripSummary"; // Import the TripSummary component
import CompletionPage from "./CompletionPage";

const PlanYourTrip = () => {
  const [days, setDays] = useState(1);
  const [destinations, setDestinations] = useState([]);
  const [selectedDestinations, setSelectedDestinations] = useState([]);
  const [plan, setPlan] = useState([]);
  const [currentPage, setCurrentPage] = useState("create");
  const [tripStartDate, setTripStartDate] = useState(null);
  const [tripEndDate, setTripEndDate] = useState(null);
  const [tripCompleted, setTripCompleted] = useState(false);

  const navigate = useNavigate();

  // Fetch destinations from the backend
  useEffect(() => {
    const fetchDestinations = async () => {
      const districts = ["Puducherry", "Mahe", "Karaikal", "Yanam"];
      const allDestinations = [];

      for (const district of districts) {
        try {
          const data = await getDestinationsByDistrict(district);
          allDestinations.push({
            district,
            destinations: Array.isArray(data?.destinations)
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

  // Handle date selection and calculate days automatically
  const handleDateChange = (date) => {
    const startDate = date[0];
    const maxEndDate = new Date(startDate);
    maxEndDate.setDate(maxEndDate.getDate() + 6); // Restrict to 7 days

    const endDate = date[1] && date[1] <= maxEndDate ? date[1] : maxEndDate;
    setTripStartDate(startDate);
    setTripEndDate(endDate);

    const diffDays = Math.ceil((endDate - startDate) / (1000 * 3600 * 24)) + 1;
    setDays(diffDays);
  };

  // Handle selection/deselection of destinations
  const handleDestinationSelect = (district, destination) => {
    setSelectedDestinations((prev) => {
      const exists = prev.some(
        (item) => item.name === destination.name && item.district === district
      );

      if (exists) {
        return prev.filter(
          (item) => !(item.name === destination.name && item.district === district)
        );
      } else {
        return [...prev, { district, ...destination }];
      }
    });
  };

  // Create the itinerary plan
  const handleCreatePlan = () => {
    if (!tripStartDate || !tripEndDate || selectedDestinations.length === 0) {
      alert("Please select dates and destinations!");
      return;
    }

    const plan = [];
    let currentDay = [];
    let currentDistrict = selectedDestinations[0]?.district;

    selectedDestinations.forEach((destination, index) => {
      if (destination.district === currentDistrict) {
        currentDay.push(destination);
      } else {
        plan.push({ day: plan.length + 1, destinations: currentDay });
        currentDay = [destination];
        currentDistrict = destination.district;
      }

      if (currentDay.length === 4 || index === selectedDestinations.length - 1) {
        plan.push({ day: plan.length + 1, destinations: currentDay });
        currentDay = [];
      }
    });

    setPlan(plan);
    setCurrentPage("summary");
  };

  // Handle visited checkbox change
  const handleVisitChange = (dayIndex, destinationIndex) => {
    const updatedPlan = [...plan];
    const destination = updatedPlan[dayIndex].destinations[destinationIndex];
    destination.visited = !destination.visited; // Toggle the visited state
    setPlan(updatedPlan);

    // Check if all destinations are visited
    const allVisited = updatedPlan.every((day) =>
      day.destinations.every((destination) => destination.visited)
    );
    if (allVisited) {
      setTripCompleted(true);
    }
  };

  // Render the component
  return (
    <div className="plan-your-trip">
      <h1>Plan Your Trip</h1>
      {currentPage === "create" && (
        <>
          <div className="date-selection centered">
            <label>Select Your Trip Dates:</label>
            <Calendar
              selectRange={true}
              onChange={handleDateChange}
              minDate={new Date()}
            />
          </div>
          <p className="days-info">
            Planning for <strong>{days}</strong> days.
          </p>

          <div className="destinations-container">
            {destinations.map(({ district, destinations }) => (
              <div key={district} className="district-section">
                <h2 className="district-title">{district}</h2>
                <div className="destination-grid">
                  {destinations.map((destination) => (
                    <div
                      key={destination.name}
                      className={`destination-card ${
                        selectedDestinations.some(
                          (item) =>
                            item.name === destination.name &&
                            item.district === district
                        )
                          ? "selected"
                          : ""
                      }`}
                      onClick={() => handleDestinationSelect(district, destination)}
                    >
                      <img
                        src={destination.imageUrl}
                        alt={destination.name}
                        className="destination-image"
                      />
                      <h3 className="destination-name">{destination.name}</h3>
                    </div>
                  ))}
                </div>
              </div>
            ))}
          </div>

          <button className="next-btn" onClick={handleCreatePlan}>
            Create Itinerary Plan
          </button>
        </>
      )}

      {currentPage === "summary" && (
        <TripSummary plan={plan} onVisitChange={handleVisitChange} />
      )}

      {tripCompleted && (
        <CompletionPage
          selectedDestinations={selectedDestinations}
          tripStartDate={tripStartDate}
          tripEndDate={tripEndDate}
        />
      )}
    </div>
  );
};

export default PlanYourTrip;
