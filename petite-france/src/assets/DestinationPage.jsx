import React, { useState, useEffect } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "../styles/DestinationPage.css"; // Ensure you style appropriately
import { Navigation, Pagination } from "swiper/modules";

const DestinationPage = () => {
  // Updated districts data, including manual image paths and temperature info
  const [districts, setDistricts] = useState([
    {
      name: "Puducherry",
      tagline: "A Blend of French and Indian Cultures",
      image: "public/assets/puducherry.jpg",
      temperature: "32°C",
      tempIcon: "🌞", // Temperature icon for Puducherry
    },
    {
      name: "Mahe",
      tagline: "Serenity by the Sea",
      image: "mahe.jpg",
      temperature: "30°C",
      tempIcon: "🌞", // Temperature icon for Mahe
    },
    {
      name: "Karaikal",
      tagline: "A Peaceful Coastal Town",
      image: "karaikal.jpg",
      temperature: "33°C",
      tempIcon: "🌞", // Temperature icon for Karaikal
    },
    {
      name: "Yanam",
      tagline: "A Blend of French Architecture",
      image: "yanam.jpg",
      temperature: "31°C",
      tempIcon: "🌞", // Temperature icon for Yanam
    },
  ]);

  return (
    <div className="destination-page">
      <h1>Explore Districts</h1>

      {/* Swiper Carousel */}
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
              <img
                src={require(`../assets/${district.image}`).default}
                alt={district.name}
                className="district-image"
              />
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
