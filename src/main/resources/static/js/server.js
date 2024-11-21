const express = require('express');
const app = express();
const port = 3000;

// Sample data for categories and destinations
const categoriesData = {
  Puducherry: ["Historical", "Beaches", "Temples", "Nature"],
  Karaikal: ["Beaches", "Pilgrimage", "Nature"],
  Mahe: ["Waterfalls", "Nature", "Adventure"],
  Yanam: ["Cultural", "Historical", "Nature"]
};

const destinationsData = {
  Puducherry: {
    "Historical": [
      { name: "Aurobindo Ashram", description: "A spiritual community with a history.", location: "Puducherry", imageUrl: "https://via.placeholder.com/200" },
      { name: "Notre-Dame des Anges", description: "French style architecture church.", location: "Puducherry", imageUrl: "https://via.placeholder.com/200" }
    ],
    "Beaches": [
      { name: "Paradise Beach", description: "A pristine beach with crystal-clear water.", location: "Puducherry", imageUrl: "https://via.placeholder.com/200" },
      { name: "Promenade Beach", description: "A beach along the Puducherry coastline.", location: "Puducherry", imageUrl: "https://via.placeholder.com/200" }
    ]
  },
  Karaikal: {
    "Beaches": [
      { name: "Karaikal Beach", description: "A serene beach with golden sands.", location: "Karaikal", imageUrl: "https://via.placeholder.com/200" }
    ]
  }
};

// Root route (for the home page)
app.get('/', (req, res) => {
  res.send('Welcome to Puducherry Tourism!');  // You can change this to serve a specific HTML file or content
});

// API endpoint to fetch categories for a state
app.get('/api/destinations/categories/:state', (req, res) => {
  const state = req.params.state;
  const categories = categoriesData[state] || [];
  res.json(categories);
});

// API endpoint to fetch destinations for a state and category
app.get('/api/destinations/:state/:category', (req, res) => {
  const { state, category } = req.params;
  const destinations = destinationsData[state]?.[category] || [];
  res.json(destinations);
});

// Serve static files (like your frontend HTML, CSS, JS)
app.use(express.static('public'));

// Start the server
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});

const multer = require('multer');
const upload = multer({ dest: 'uploads/' });

app.post('/api/destinations', upload.single('image'), (req, res) => {
    // Handle destination data and image upload here
    const destinationData = req.body; // non-file fields
    const imageFile = req.file; // uploaded image
    // Process and save destination and image
});
