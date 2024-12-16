import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DistrictDetailPage from './pages/DistrictDetailPage';
import DestinationPage from './pages/DestinationPage';
import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import PlanYourTrip from './components/PlanYourTrip/PlanYourTrip';
import CompletionPage from './components/PlanYourTrip/CompletionPage';
import './App.css'; 


function App() {
  return (
    <Router>
      <Header  />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/destinations" element={<DestinationPage />} />
        <Route path="/districts/:districtName" element={<DistrictDetailPage />} />
        <Route path="/plan-your-trip" element={<PlanYourTrip />} />
        <Route path="/completion-page" element={<CompletionPage />} />
        <Route exact path="/" component={PlanYourTrip} />
        </Routes>
      <Footer  />
    </Router>
  );
}

export default App;
