const express = require('express');
const multer = require('multer');
const { Server, OPEN } = require('ws');
const mongoose = require('mongoose');
const path = require('path');

const app = express();
const port = 3000;

// Middleware to parse JSON and URL-encoded data
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// MongoDB Database Connection
mongoose.connect('mongodb://localhost:27017/destinationDB', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
}).then(() => {
  console.log('Connected to MongoDB database');
}).catch((err) => {
  console.error('Error connecting to MongoDB:', err.message);
});

// Define Destination Schema for MongoDB
const destinationSchema = new mongoose.Schema({
  name: String,
  description: String,
  state: String,
  category: String,
  image_url: String,
});

const Destination = mongoose.model('Destination', destinationSchema);

// Configure Multer for file uploads
const upload = multer({
  dest: 'uploads/',
  limits: { fileSize: 10 * 1024 * 1024 }  // 10 MB limit
});

// Root route
app.get('/', (req, res) => {
  res.send('Welcome to Puducherry Tourism!');
});

// API endpoint to fetch categories for a state
app.get('/api/destinations/categories/:state', (req, res) => {
  const state = req.params.state;

  Destination.distinct('category', { state })
    .then((categories) => {
      res.json(categories);
    })
    .catch((err) => {
      console.error(err.message);
      res.status(500).send('Error fetching categories');
    });
});

// API endpoint to fetch destinations for a state and category
app.get('/api/destinations/:state/:category', (req, res) => {
  const { state, category } = req.params;

  Destination.find({ state, category })
    .then((destinations) => {
      res.json(destinations);
    })
    .catch((err) => {
      console.error(err.message);
      res.status(500).send('Error fetching destinations');
    });
});

// API endpoint to add a destination with an image
app.post('/api/destinations', upload.single('image'), (req, res) => {
  const { name, description, state, category } = req.body;
  const imagePath = req.file ? req.file.path : null;

  const newDestination = new Destination({
    name,
    description,
    state,
    category,
    image_url: imagePath,
  });

  newDestination.save()
    .then(() => {
      res.status(201).send('Destination added successfully');
    })
    .catch((err) => {
      console.error(err.message);
      res.status(500).send('Error adding destination');
    });
});

// Serve static files
app.use(express.static(path.join(__dirname, 'public')));

// Start the server
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});

// Serve files from the 'uploads' folder
app.use('/uploads', express.static('uploads'));

// Initialize WebSocket server
const wss = new Server({ port: 8080 });

wss.on('connection', (ws) => {
  console.log('Client connected via WebSocket');

  ws.on('message', (message) => {
    console.log('Received:', message);
    // Broadcast the message to all connected clients
    wss.clients.forEach((client) => {
      if (client.readyState === OPEN) {
        client.send(message);
      }
    });
  });

  ws.on('close', () => {
    console.log('Client disconnected');
  });
});
