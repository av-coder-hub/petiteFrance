import React from "react";
import './Header.css'; // Header styles
import logoImage from '../assets/logo.png'; // Your logo image

function Header() {
  return (
    <header className="header-container">
      <div className="navbar-container">
        <div className="logo-container">
          <img src={logoImage} alt="Explore Petite France" className="logo-img" />
          <h1 className="tagline">Explore Petite France</h1>
        </div>

        <nav className="navbar">
          <ul className="navbar-list">
            <li className="nav-item"><a href="/destinations">Destinations</a></li>
            <li className="nav-item"><a href="/things-to-explore">Things to Explore</a></li>
            <li className="nav-item"><a href="/plan-your-trip">Plan Your Trip</a></li>
            <li className="nav-item"><a href="/calendar">Calendar</a></li>
            <li className="nav-item"><a href="/map">Map</a></li>
            <li className="nav-item"><a href="/search">Search</a></li>
            <li className="nav-item"><a href="/login">Login/Sign Up</a></li>
          </ul>
        </nav>
      </div>
    </header>
  );
}

export default Header;
