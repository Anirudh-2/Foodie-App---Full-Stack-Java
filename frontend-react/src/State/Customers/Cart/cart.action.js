import { admin_api, api } from "../../../config/api";
import {
  findCartFailure,
  findCartRequest,
  findCartSuccess,
  getAllCartItemsFailure,
  getAllCartItemsRequest,
  getAllCartItemsSuccess,
} from "./ActionCreators";
import {
  ADD_ITEM_TO_CART_FAILURE,
  ADD_ITEM_TO_CART_REQUEST,
  ADD_ITEM_TO_CART_SUCCESS,
  CLEARE_CART_FAILURE,
  CLEARE_CART_REQUEST,
  CLEARE_CART_SUCCESS,
  REMOVE_CARTITEM_FAILURE,
  REMOVE_CARTITEM_REQUEST,
  REMOVE_CARTITEM_SUCCESS,
  UPDATE_CARTITEM_FAILURE,
  UPDATE_CARTITEM_REQUEST,
  UPDATE_CARTITEM_SUCCESS,
  FIND_CART_REQUEST,
  FIND_CART_SUCCESS,
  FIND_CART_FAILURE,
  GET_ALL_CART_ITEMS_REQUEST,
  GET_ALL_CART_ITEMS_SUCCESS,
  GET_ALL_CART_ITEMS_FAILURE,
} from "./ActionTypes";

// ✅ Find Cart
export const findCart = (jwt) => {
  return async (dispatch) => {
    dispatch(findCartRequest());
    try {
      const { data } = await api.get("/api/cart", {
        headers: { Authorization: `Bearer ${jwt}` },
      });
      console.log("Cart Items: ", data);
      dispatch(findCartSuccess(data));
    } catch (error) {
      console.error("Error fetching cart:", error.response?.data || error.message);
      dispatch(findCartFailure(error.response?.data));
    }
  };
};

// ✅ Get All Cart Items
export const getAllCartItems = (token) => async (dispatch) => {
  dispatch(getAllCartItemsRequest());
  try {
    const { data } = await admin_api.get("/api/cart/items", {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch(getAllCartItemsSuccess(data));
  } catch (error) {
    dispatch(getAllCartItemsFailure(error.response?.data?.message || "Error fetching cart items"));
  }
};

// ✅ Add Item to Cart
export const addItemToCart = (token, cartItem) => async (dispatch) => {
  dispatch({ type: ADD_ITEM_TO_CART_REQUEST });
  try {
    const { data } = await admin_api.put("/api/cart/add", cartItem, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: ADD_ITEM_TO_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: ADD_ITEM_TO_CART_FAILURE, payload: error.response?.data?.message || "Failed to add item" });
  }
};

// ✅ Update Cart Item
export const updateCartItem = (token, cartItem) => async (dispatch) => {
  dispatch({ type: UPDATE_CARTITEM_REQUEST });
  try {
    const { data } = await admin_api.put("/api/cart/item/update", cartItem, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: UPDATE_CARTITEM_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: UPDATE_CARTITEM_FAILURE, payload: error.response?.data?.message || "Failed to update item" });
  }
};

// ✅ Remove Cart Item
export const removeCartItem = (token, cartItemId) => async (dispatch) => {
  dispatch({ type: REMOVE_CARTITEM_REQUEST });
  try {
    await admin_api.delete(`/api/cart/item/${cartItemId}/remove`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: REMOVE_CARTITEM_SUCCESS, payload: cartItemId });
  } catch (error) {
    dispatch({ type: REMOVE_CARTITEM_FAILURE, payload: error.response?.data?.message || "Failed to remove item" });
  }
};

// ✅ Clear Cart
export const clearCartAction = (token) => async (dispatch) => {
  dispatch({ type: CLEARE_CART_REQUEST });
  try {
    const { data } = await admin_api.put("/api/cart/clear", {}, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: CLEARE_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: CLEARE_CART_FAILURE, payload: error.response?.data?.message || "Failed to clear cart" });
  }
};
