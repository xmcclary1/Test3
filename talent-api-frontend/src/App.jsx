import "./App.css";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Header from "./components/Header.jsx";
import Home from "./components/Home";
import Register from "./components/Register.jsx";
import Login from "./components/Login";
import CandidateHomepage from "./components/CandidateHomepage.jsx";

function App() {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
        <Route path="/" element={<Home />} />
          <Route path="/Home" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/candidate" element={<CandidateHomepage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
