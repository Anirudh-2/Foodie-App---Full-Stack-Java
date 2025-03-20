import axios from 'axios';

export const API_URL = "http://localhost:8181";
export const ADMIN_API_URL = "http://localhost:8080";


export const api = axios.create({
  baseURL: API_URL, 
  headers: {
    'Content-Type': 'application/json',
  },
});

export const admin_api = axios.create({
  baseURL: ADMIN_API_URL, 
  headers: {
    'Content-Type': 'application/json',
  },
});


