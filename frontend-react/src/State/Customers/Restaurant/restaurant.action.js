import { api, admin_api } from "../../../config/api";
import axios from "axios";
import {
  createRestaurantFailure,
  createRestaurantRequest,
  createRestaurantSuccess,
  deleteRestaurantFailure,
  deleteRestaurantRequest,
  deleteRestaurantSuccess,
  getAllRestaurantsFailure,
  getAllRestaurantsRequest,
  getAllRestaurantsSuccess,
  getRestaurantByIdFailure,
  getRestaurantByIdRequest,
  getRestaurantByIdSuccess,
  updateRestaurantFailure,
  updateRestaurantRequest,
  updateRestaurantSuccess,
} from "./ActionCreators";

import {
  CREATE_CATEGORY_FAILURE,
  CREATE_CATEGORY_REQUEST,
  CREATE_CATEGORY_SUCCESS,
  CREATE_EVENTS_FAILURE,
  CREATE_EVENTS_REQUEST,
  CREATE_EVENTS_SUCCESS,
  DELETE_EVENTS_FAILURE,
  DELETE_EVENTS_REQUEST,
  DELETE_EVENTS_SUCCESS,
  GET_ALL_EVENTS_FAILURE,
  GET_ALL_EVENTS_REQUEST,
  GET_ALL_EVENTS_SUCCESS,
  GET_RESTAURANTS_EVENTS_FAILURE,
  GET_RESTAURANTS_EVENTS_REQUEST,
  GET_RESTAURANTS_EVENTS_SUCCESS,
  GET_RESTAURANTS_CATEGORY_FAILURE,
  GET_RESTAURANTS_CATEGORY_REQUEST,
  GET_RESTAURANTS_CATEGORY_SUCCESS,
  GET_RESTAURANT_BY_USER_ID_FAILURE,
  GET_RESTAURANT_BY_USER_ID_REQUEST,
  GET_RESTAURANT_BY_USER_ID_SUCCESS,
  UPDATE_RESTAURANT_STATUS_FAILURE,
  UPDATE_RESTAURANT_STATUS_REQUEST,
  UPDATE_RESTAURANT_STATUS_SUCCESS,
} from "./ActionTypes";

const handleError = (error, dispatch, failureActionType) => {
  const message = error.response?.data || error.message || "Unknown error occurred.";
  dispatch({ type: failureActionType, payload: message });
  console.error("API Error:", message, error);
};

const apiRequest = async (method, url, jwt, data = null) => {
  const config = {
    method,
    url: `http://localhost:8080${url}`,
    headers: { Authorization: `Bearer ${jwt}` },
    data,
  };

  try {
    const response = await axios(config);
    return response.data;
  } catch (error) {
    handleError(error, console.error, CREATE_CATEGORY_FAILURE);
    throw error;
  }
};


// Fetch all restaurants
export const getAllRestaurantsAction = (jwt) => async (dispatch) => {
  dispatch(getAllRestaurantsRequest());
  try {
    const data = await apiRequest("get", "/api/restaurants", jwt);
    dispatch(getAllRestaurantsSuccess(data));
  } catch (error) {
    handleError(error, dispatch, getAllRestaurantsFailure);
  }
};

// Fetch restaurant by ID
export const getRestaurantById = ({ restaurantId, jwt }) => async (dispatch) => {
  dispatch(getRestaurantByIdRequest());
  try {
    const data = await apiRequest("get", `/api/restaurants/${restaurantId}`, jwt);
    dispatch(getRestaurantByIdSuccess(data));
  } catch (error) {
    handleError(error, dispatch, getRestaurantByIdFailure);
  }
};


// Create restaurant
export const createRestaurant = ({ data, token }) => async (dispatch) => {
  dispatch(createRestaurantRequest());
  try {
    const responseData = await apiRequest("post", "/api/admin/restaurants", token, data);
    dispatch(createRestaurantSuccess(responseData));
  } catch (error) {
    handleError(error, dispatch, createRestaurantFailure);
  }
};

// Update restaurant
export const updateRestaurant = ({ restaurantId, restaurantData, jwt }) => async (dispatch) => {
  dispatch(updateRestaurantRequest());
  try {
    const data = await apiRequest("put", `/api/admin/restaurants/${restaurantId}`, jwt, restaurantData);
    dispatch(updateRestaurantSuccess(data));
  } catch (error) {
    handleError(error, dispatch, updateRestaurantFailure);
  }
};

// Delete restaurant
export const deleteRestaurant = ({ restaurantId, jwt }) => async (dispatch) => {
  dispatch(deleteRestaurantRequest());
  try {
    await apiRequest("delete", `/api/admin/restaurants/${restaurantId}`, jwt);
    dispatch(deleteRestaurantSuccess(restaurantId));
  } catch (error) {
    handleError(error, dispatch, deleteRestaurantFailure);
  }
};


// Update restaurant status
export const updateRestaurantStatus = ({ restaurantId, jwt, status }) => async (dispatch) => {
  dispatch({ type: UPDATE_RESTAURANT_STATUS_REQUEST });
  if (!status) {
    dispatch({ type: UPDATE_RESTAURANT_STATUS_FAILURE, payload: "Status is required" });
    return;
  }
  try {
    const data = await apiRequest("put", `/api/admin/restaurants/${restaurantId}/status`, jwt, { status });
    dispatch({ type: UPDATE_RESTAURANT_STATUS_SUCCESS, payload: data });
  } catch (error) {
    handleError(error, dispatch, UPDATE_RESTAURANT_STATUS_FAILURE);
  }
};

// Create category
export const createCategoryAction = ({ reqData, jwt }) => async (dispatch) => {
  dispatch({ type: CREATE_CATEGORY_REQUEST });
  try {
    const data = await apiRequest("post", "/api/admin/category", jwt, reqData);
    dispatch({ type: CREATE_CATEGORY_SUCCESS, payload: data });
  } catch (error) {
    handleError(error, dispatch, CREATE_CATEGORY_FAILURE);
  }
};

// Create event
export const createEventAction = ({ data, restaurantId, jwt }) => async (dispatch) => {
  dispatch({ type: CREATE_EVENTS_REQUEST });
  try {
    const responseData = await apiRequest("post", `/api/admin/restaurants/${restaurantId}/events`, jwt, data);
    dispatch({ type: CREATE_EVENTS_SUCCESS, payload: responseData });
  } catch (error) {
    handleError(error, dispatch, CREATE_EVENTS_FAILURE);
  }
};

export const getAllEventsAction = (jwt) => async (dispatch) => {
  dispatch({ type: GET_ALL_EVENTS_REQUEST });
  try {
    const data = await apiRequest("get", "/api/admin/events", jwt);
    dispatch({ type: GET_ALL_EVENTS_SUCCESS, payload: data });
  } catch (error) {
    handleError(error, dispatch, GET_ALL_EVENTS_FAILURE);
  }
};

export const getRestaurantsEvents = ({ restaurantId, jwt }) => async (dispatch) => {
  dispatch({ type: GET_RESTAURANTS_EVENTS_REQUEST });
  try {
    const data = await apiRequest("get", `/api/admin/restaurants/${restaurantId}/events`, jwt);
    dispatch({ type: GET_RESTAURANTS_EVENTS_SUCCESS, payload: data });
  } catch (error) {
    handleError(error, dispatch, GET_RESTAURANTS_EVENTS_FAILURE);
  }
};

// Delete event
export const deleteEventAction = (eventId, jwt) => async (dispatch) => {
  dispatch({ type: DELETE_EVENTS_REQUEST });
  try {
    await apiRequest("delete", `/api/admin/events/${eventId}`, jwt);
    dispatch({ type: DELETE_EVENTS_SUCCESS, payload: eventId });
  } catch (error) {
    handleError(error, dispatch, DELETE_EVENTS_FAILURE);
  }
};

export const getRestaurantsCategory = ({ jwt, restaurantId }) => async (dispatch) => {
  dispatch({ type: GET_RESTAURANTS_CATEGORY_REQUEST });
  try {
    const response = await axios.get(`http://localhost:8080/api/restaurants/${restaurantId}/categories`, {
      headers: { Authorization: `Bearer ${jwt}` },
    });
    dispatch({
      type: GET_RESTAURANTS_CATEGORY_SUCCESS,
      payload: response.data,
    });
  } catch (error) {
    dispatch({
      type: GET_RESTAURANTS_CATEGORY_FAILURE,
      payload: error.message,
    });
  }
};

export const getRestaurantByUserId = (jwt) => async (dispatch) => {
  dispatch({ type: GET_RESTAURANT_BY_USER_ID_REQUEST });
  try {
    const data = await apiRequest("get", "/api/restaurants/user", jwt);
    dispatch({ type: GET_RESTAURANT_BY_USER_ID_SUCCESS, payload: data });
  } catch (error) {
    dispatch({
      type: GET_RESTAURANT_BY_USER_ID_FAILURE,
      payload: error.message || "Failed to fetch restaurant.",
    });
  }
};

