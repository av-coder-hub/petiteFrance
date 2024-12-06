import React, { useState } from "react";
import "./VideoBanner.css";
import video from "../assets/pondicherry-loop.mp4"; // Import your video file

function VideoBanner() {
  const [isPlaying, setIsPlaying] = useState(true);

  const handlePlayPause = () => {
    const videoElement = document.getElementById("background-video");
    if (isPlaying) {
      videoElement.pause();
    } else {
      videoElement.play();
    }
    setIsPlaying(!isPlaying);
  };

  return (
    <div className="video-banner">
      <video
        id="background-video"
        src={video}
        autoPlay
        loop
        muted
        className="background-video"
      />
      <div className="video-overlay">
        <h1 className="video-tagline">Welcome to India's Mini France</h1>
        <p className="video-desc">Discover the beauty and charm of Pondicherry</p>
        <button onClick={handlePlayPause}>
          {isPlaying ? "Pause" : "Play"}
        </button>
      </div>
    </div>
  );
}

export default VideoBanner;
