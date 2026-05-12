import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home.jsx";
import Today from "./pages/Today.jsx";

import "./styles/App.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/today" element={<Today />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
