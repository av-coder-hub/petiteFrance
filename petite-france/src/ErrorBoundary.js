// src/ErrorBoundary.js
import React, { Component } from "react";

class ErrorBoundary extends Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error) {
    // Update state to indicate error
    return { hasError: true };
  }

  componentDidCatch(error, info) {
    // Log the error details
    console.log("Error:", error);
    console.log("Error info:", info);
  }

  render() {
    if (this.state.hasError) {
      // Fallback UI when error occurs
      return <h2>Something went wrong!</h2>;
    }
    return this.props.children; // Render child components if no error
  }
}

export default ErrorBoundary;
