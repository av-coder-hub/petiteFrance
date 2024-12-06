import React from "react";
import './HomePage.css';
import VideoBanner from "../components/VideoBanner";
import EventSection from "../components/EventSection";

function HomePage() {
  return (
    <div>
      <VideoBanner />
      <EventSection />
    </div>
  );
}

export default HomePage;
