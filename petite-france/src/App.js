import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage";
import EventSection from './components/EventSection'; // Event Section
import Footer from './components/Footer'; // Footer
import './App.css'; // General styles

function App() {
  return (
    <Router>
      <Header />
      <div className="main-content">
        <Routes>
          <Route path="/" element={<HomePage />} />
        </Routes>
      </div>
      <Footer /> {/* Footer at the bottom of the page */}
    </Router>
  );
}

export default App;
