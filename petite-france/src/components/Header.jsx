import React from "react";
import { Link } from "react-router-dom";
import '../styles/Header.css'; // Header styles
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
            <li className="nav-item dropdown">
              <Link to="/destinations">Destinations</Link>
              <ul className="dropdown-menu">
                <li><Link to="/districts">Puducherry</Link></li>
                <li><Link to="/districts/puducherry">Pondicherry</Link></li>
                <li><Link to="/districts/karaikal">Karaikal</Link></li>
                <li><Link to="/districts/mahe">Mahe</Link></li>
                <li><Link to="/districts/yanam">Yanam</Link></li>
              </ul>
            </li>
            <li className="nav-item"><Link to="/things-to-explore">Things to Explore</Link></li>
            <li className="nav-item"><Link to="/plan-your-trip">Plan Your Trip</Link></li>
            <li className="nav-item"><Link to="/calendar">Calendar</Link></li>
            <li className="nav-item"><Link to="/map">Map</Link></li>
            <li className="nav-item"><Link to="/search">Search</Link></li>
            <li className="nav-item"><Link to="/login">Login/Sign Up</Link></li>
          </ul>
        </nav>
      </div>
    </header>
  );
}

export default Header;
