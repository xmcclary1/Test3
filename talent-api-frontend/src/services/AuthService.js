import axios from "axios";

const AUTH_REST_API_BASE_URL = "http://localhost:8080/api/auth";

// API Calls
export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL + '/register', registerObj);

export const loginAPICall = (usernameOrEmail, password) => axios.post(AUTH_REST_API_BASE_URL + '/login', { usernameOrEmail, password});

// Token Management
export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

// User Session Management
export const saveLoggedInUser = (username, role,userId) => {
    sessionStorage.setItem("authenticatedUser", username);
    sessionStorage.setItem("role", role);
    sessionStorage.setItem("userId", userId);
}

export const isUserLoggedIn = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username !== null;
}

export const getLoggedInUser = () => {
    return sessionStorage.getItem("authenticatedUser");
}

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
}

// Role Check Functions
export const isManager = () => {
    const role = sessionStorage.getItem("role");
    return role === 'manager';
}

export const isCandidate = () => {
    const role = sessionStorage.getItem("role");
    return role === 'candidate';
}

