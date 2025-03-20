import axios from "axios";
import {
  UPDATE_ORDER_STATUS_REQUEST,
  UPDATE_ORDER_STATUS_SUCCESS,
  UPDATE_ORDER_STATUS_FAILURE,
  GET_RESTAURANTS_ORDER_REQUEST,
  GET_RESTAURANTS_ORDER_SUCCESS,
  GET_RESTAURANTS_ORDER_FAILURE,
} from "./ActionType.js";
import { api, admin_api } from "../../../config/api.js";

// Update Order Status Action
export const updateOrderStatus = ({ orderId, orderStatus, jwt }) => {
  return async (dispatch) => {
    try {
      dispatch({ type: UPDATE_ORDER_STATUS_REQUEST });

      // Ensure JWT token is provided
      if (!jwt || !jwt.includes('.')) {
        throw new Error('Invalid JWT token');
      }

      const response = await admin_api.put(
        `/api/admin/orders/${orderId}/${orderStatus}`,
        {},  // Empty body as per the API design
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      const updatedOrder = response.data;
      console.log("Updated order: ", updatedOrder);

      dispatch({
        type: UPDATE_ORDER_STATUS_SUCCESS,
        payload: updatedOrder,
      });
    } catch (error) {
      console.error("Error updating order status: ", error.response || error.message);

      const errorMessage = error.response
        ? error.response.data.message || error.response.statusText
        : error.message;

      dispatch({
        type: UPDATE_ORDER_STATUS_FAILURE,
        error: errorMessage,
      });
    }
  };
};

// Fetch Restaurant Orders Action
export const fetchRestaurantsOrder = ({ restaurantId, orderStatus, jwt }) => {
  return async (dispatch) => {
    try {
      dispatch({ type: GET_RESTAURANTS_ORDER_REQUEST });

      // Validate restaurantId as a MongoDB ObjectId (24-character hex string)
      if (!restaurantId || restaurantId.length !== 24 || !/^[0-9a-fA-F]{24}$/.test(restaurantId)) {
        console.error("Invalid Restaurant ID format");
        dispatch({
          type: GET_RESTAURANTS_ORDER_FAILURE,
          error: "Invalid Restaurant ID format",
        });
        return;
      }

      // Ensure JWT token is provided
      if (!jwt || !jwt.includes('.')) {
        console.error("Invalid JWT token");
        dispatch({
          type: GET_RESTAURANTS_ORDER_FAILURE,
          error: "Invalid JWT token",
        });
        return;
      }

      // Fetch the orders with orderStatus as a query parameter (if provided)
      const response = await admin_api.get(
        `/api/admin/order/restaurant/${restaurantId}`,
        {
          params: orderStatus ? { orderStatus } : {}, // Correct query parameter key
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      const orders = response.data;
      console.log("Fetched restaurant orders: ", orders);

      dispatch({
        type: GET_RESTAURANTS_ORDER_SUCCESS,
        payload: orders,
      });
    } catch (error) {
      console.error("Error fetching restaurant orders: ", error.response || error.message);

      const errorMessage = error.response
        ? error.response.data.message || error.response.statusText
        : error.message;

      dispatch({
        type: GET_RESTAURANTS_ORDER_FAILURE,
        error: errorMessage,
      });
    }
  };
};