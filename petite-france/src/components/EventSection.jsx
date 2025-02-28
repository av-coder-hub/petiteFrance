import React, { useState, useEffect } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/navigation'; 
import 'swiper/css/pagination'; 
import './../styles/EventSection.css'; // Your custom styles
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper/modules';
import axios from 'axios'; // To make HTTP requests

function EventSection() {
  // State to store the events
  const [events, setEvents] = useState([]);

  // Fetch events from the backend API
  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get("http://localhost:8080/events");
        setEvents(response.data); // Assuming your API returns the event list
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };
    
    fetchEvents(); // Call the fetch function
  }, []); // Empty array ensures this runs once when the component is mounted

  return (
    <div className="event-section">
      <h2 className="event-title">What's Happening This Month</h2>
      <Swiper
        modules={[Navigation, Pagination, Scrollbar, A11y]}
        spaceBetween={20}
        slidesPerView={3}
        navigation
        pagination={{ clickable: true }}
        scrollbar={{ draggable: true }}
      >
        {events.map((event) => (
          <SwiperSlide key={event.id} className="event-slide">
            <div className="event-card">
              <img src={event.imageUrl} alt={event.name} className="event-image" />
              <div className="event-info">
                <div className="event-meta-left">
                  <p>{event.location}</p>
                  <p>{event.category}</p>
                </div>
                <div className="event-meta-right">
                  <h3>{event.name}</h3>
                  <p className="event-date">{event.date}</p>
                </div>
              </div>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}

export default EventSection;
