// src/components/Footer.jsx
import React from 'react';
import './Footer.css'; // Link to the CSS for styling
import { FaFacebook, FaTwitter, FaInstagram, FaLinkedin } from 'react-icons/fa';

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-content">
          <h3>Explore Petite France</h3>
          <p>Discover the hidden gems of India's Mini France.</p>
        </div>

        <div className="footer-links">
          <ul>
            <li><a href="/contact">Contact</a></li>
            <li><a href="/privacy">Privacy Policy</a></li>
            <li><a href="/terms">Terms & Conditions</a></li>
          </ul>
        </div>

        <div className="footer-social">
          <a href="https://facebook.com" target="_blank" rel="noopener noreferrer"><FaFacebook /></a>
          <a href="https://twitter.com" target="_blank" rel="noopener noreferrer"><FaTwitter /></a>
          <a href="https://instagram.com" target="_blank" rel="noopener noreferrer"><FaInstagram /></a>
          <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer"><FaLinkedin /></a>
        </div>
      </div>

      <div className="footer-bottom">
        <p>&copy; 2024 Petite France. All rights reserved.</p>
      </div>
    </footer>
  );
}

export default Footer;
