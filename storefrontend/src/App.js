import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './Home';
import Contact from './Contact';

function App() {
  return (
    <Router>
      <div className="App">
        <nav className="navbar">
          <li className="nav-item">
            <a className="nav-link" href="/Products">Products</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/ShoppingCart">Orders</a>
          </li>
        </nav>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Products" element={<ProductPage />} />
          <Route path="/Contact" element={<Contact />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
