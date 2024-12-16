import React, { useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Link } from "react-router-dom";  // Import Link from react-router-dom
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "../styles/DestinationPage.css";
import { Navigation, Pagination } from "swiper/modules";

const DestinationPage = () => {
  const [districts, setDistricts] = useState([
    {
      name: "Puducherry",
      tagline: "A Blend of French and Indian Cultures",
      image: "puducherry.jpg",
      temperature: "32°C",
      tempIcon: "🌞",
    },
    {
      name: "Mahe",
      tagline: "Serenity by the Sea",
      image: "mahe.jpg",
      temperature: "30°C",
      tempIcon: "🌞",
    },
    {
      name: "Karaikal",
      tagline: "A Peaceful Coastal Town",
      image: "karaikal.jpg",
      temperature: "33°C",
      tempIcon: "🌞",
    },
    {
      name: "Yanam",
      tagline: "A Blend of French Architecture",
      image: "yanam.jpg",
      temperature: "31°C",
      tempIcon: "🌞",
    },
  ]);

  return (
    <div className="destination-page">
      <h1>Explore Districts</h1>

      <Swiper
        modules={[Navigation, Pagination]}
        spaceBetween={20}
        slidesPerView={3}
        navigation
        pagination={{ clickable: true }}
        className="districts-swiper"
      >
        {districts.map((district, index) => (
          <SwiperSlide key={index} className="district-card">
            <div>
              <Link to={`/destinations/${district.name}`} className="district-link">
                <img
                  src={`/assets/${district.image}`}  // Path from the public folder
                  alt={district.name}
                  className="district-image"
                />
              </Link>
              <h2>{district.name}</h2>
              <p>{district.tagline}</p>
              <div className="district-info">
                <span className="district-temperature">
                  {district.temperature} {district.tempIcon}
                </span>
              </div>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};

export default DestinationPage;
