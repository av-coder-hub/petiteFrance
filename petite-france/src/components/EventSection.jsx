import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/navigation'; /* If you're using navigation */
import 'swiper/css/pagination'; /* If you're using pagination */
import './EventSection.css'; // Your custom styles
import eventImage1 from '../assets/event-image1.jpg';
import eventImage2 from '../assets/event-image2.jpg';
import eventImage3 from '../assets/event-image3.jpg';
import eventImage4 from '../assets/event-image4.jpg';
import eventImage5 from '../assets/event-image5.jpg';

// Swiper core components
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper/modules';

function EventSection() {
  // Event data
  const events = [
    { id: 1, img: eventImage1, title: 'Event 1', location: 'Puducherry | Nature', date: '12th Dec 2024' },
    { id: 2, img: eventImage2, title: 'Event 2', location: 'Karaikal | Culture', date: '15th Dec 2024' },
    { id: 3, img: eventImage3, title: 'Event 3', location: 'Mahe | Adventure', date: '18th Dec 2024' },
    { id: 4, img: eventImage4, title: 'Event 4', location: 'Yanam | Heritage', date: '22nd Dec 2024' },
    { id: 5, img: eventImage5, title: 'Event 5', location: 'Puducherry | Art', date: '25th Dec 2024' },
  ];

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
              <img src={event.img} alt={event.title} className="event-image" />
              <div className="event-info">
                <div className="event-meta-left">
                  <p>{event.location}</p>
                  <p>{event.category}</p>
                </div>
                <div className="event-meta-right">
                  <h3>{event.title}</h3>
                  <p className="event-date">{event.date}</p> {/* Date Added */}
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
