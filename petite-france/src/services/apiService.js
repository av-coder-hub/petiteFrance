// src/services/apiService.js
const apiUrl = "http://localhost:8080/districts";

export const getDestinationsByDistrict = async (districtName) => {
  try {
    const response = await fetch(`${apiUrl}/${districtName}`);
    if (!response.ok) {
      throw new Error("Failed to fetch destinations");
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    return [];
  }
};
