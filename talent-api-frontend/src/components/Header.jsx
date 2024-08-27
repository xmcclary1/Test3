import React from "react";
import { NavLink } from "react-router-dom";
import { isUserLoggedIn, isManager, isCandidate, logout } from "../services/AuthService";
import { useNavigate } from "react-router-dom";

const Header = () => {
  const isAuth = isUserLoggedIn();
  const navigate = useNavigate();

  function handleLogout() {
    logout();
    navigate("/login");
  }

  return (
    <nav className="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
      <div className="container-fluid">
        <NavLink className="navbar-brand" to="/">Talent Connect</NavLink>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarColor01"
          aria-controls="navbarColor01"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarColor01">
          <ul className="navbar-nav me-auto">
            <li className="nav-item">
              <NavLink to="/" className="nav-link">Home</NavLink>
            </li>

            {isAuth && isCandidate() && (
              <li className="nav-item">
                <NavLink to="/my-applications" className="nav-link">My Applications</NavLink>
              </li>
            )}
          </ul>
          <ul className="navbar-nav">
            {!isAuth && (
              <>
                <li className="nav-item">
                  <NavLink to="/register" className="nav-link">Register</NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/login" className="nav-link">Login</NavLink>
                </li>
              </>
            )}

            {isAuth && (
              <li className="nav-item">
                <NavLink to="/login" className="nav-link" onClick={handleLogout}>Logout</NavLink>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Header;
