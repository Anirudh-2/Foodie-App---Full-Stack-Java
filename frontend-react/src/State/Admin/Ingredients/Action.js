import axios from 'axios';
import { 
  CREATE_INGREDIENT_CATEGORY_FAILURE, 
  CREATE_INGREDIENT_CATEGORY_SUCCESS, 
  CREATE_INGREDIENT_SUCCESS, 
  GET_INGREDIENTS, 
  GET_INGREDIENT_CATEGORY_FAILURE, 
  GET_INGREDIENT_CATEGORY_SUCCESS, 
  UPDATE_STOCK, 
  DELETE_CATEGORY, 
  DELETE_INGREDIENT 
} from './ActionType';
import { admin_api } from '../../../config/api';

// Fetch Ingredients of a Restaurant
export const getIngredientsOfRestaurant = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      if (!id) throw new Error("Restaurant ID is missing");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.get(`/api/admin/ingredients/restaurant/${id}`, {
        headers: { Authorization: `Bearer ${jwt}` },
      });

      console.log("Fetched ingredients for restaurant: ", response.data);
      dispatch({ type: GET_INGREDIENTS, payload: response.data });
    } catch (error) {
      console.error("Error fetching ingredients: ", error.response?.data || error.message);
    }
  };
};

// Create a new Ingredient
export const createIngredient = ({ data, jwt }) => {
  return async (dispatch) => {
    try {
      if (!data) throw new Error("Ingredient data is missing");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.post(`/api/admin/ingredients`, data, {
        headers: { Authorization: `Bearer ${jwt}` },
      });

      console.log("Created ingredient: ", response.data);
      dispatch({ type: CREATE_INGREDIENT_SUCCESS, payload: response.data });
    } catch (error) {
      console.error("Error creating ingredient:", error.response?.data || error.message);
    }
  };
};

// Create a new Ingredient Category
export const createIngredientCategory = ({ data, jwt }) => {
  return async (dispatch) => {
    try {
      if (!data) throw new Error("Category data is missing");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.post(`/api/admin/ingredients/category`, data, {
        headers: { Authorization: `Bearer ${jwt}` },
      });

      console.log("Created ingredient category: ", response.data);
      dispatch({ type: CREATE_INGREDIENT_CATEGORY_SUCCESS, payload: response.data });
    } catch (error) {
      console.error("Error creating ingredient category:", error.response?.data || error.message);
    }
  };
};

// Fetch Ingredient Categories of a Restaurant
export const getIngredientCategory = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      if (!id) throw new Error("Restaurant ID is missing");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.get(`/api/admin/ingredients/restaurant/${id}/category`, {
        headers: { Authorization: `Bearer ${jwt}` },
      });

      console.log("Fetched ingredient categories: ", response.data);
      dispatch({ type: GET_INGREDIENT_CATEGORY_SUCCESS, payload: response.data });
    } catch (error) {
      console.error("Error fetching ingredient categories: ", error.response?.data || error.message);
    }
  };
};

// Update the Stock of an Ingredient
export const updateStockOfIngredient = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      if (!id) throw new Error("Ingredient ID is missing for stock update");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.put(
        `/api/admin/ingredients/${id}/stock`, // Fixed endpoint
        {},
        { headers: { Authorization: `Bearer ${jwt}` } }
      );

      console.log("Updated ingredient stock: ", response.data);
      dispatch({ type: UPDATE_STOCK, payload: response.data });
    } catch (error) {
      console.error("Error updating stock for ingredient: ", error.response?.data || error.message);
    }
  };
};

// Delete an Ingredient
export const deleteIngredient = (id, jwt) => {
  return async (dispatch) => {
    try {
      if (!id) throw new Error("Ingredient ID is missing");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.delete(`/api/admin/ingredients/${id}`, {
        headers: { Authorization: `Bearer ${jwt}` },
      });

      if (response.status === 200) {
        dispatch({ type: DELETE_INGREDIENT, payload: id });
        console.log("Ingredient deleted successfully.");
      }
    } catch (error) {
      console.error("Error deleting ingredient:", error.response?.data || error.message);
    }
  };
};

// Delete a Category
export const deleteCategory = (id, jwt) => {
  return async (dispatch) => {
    try {
      if (!id) throw new Error("Category ID is missing");
      if (!jwt) throw new Error("JWT is missing");

      const response = await admin_api.delete(`/api/admin/ingredients/category/${id}`, {
        headers: { Authorization: `Bearer ${jwt}` },
      });

      if (response.status === 200) {
        dispatch({ type: DELETE_CATEGORY, payload: id });
        console.log("Category deleted successfully.");
      }
    } catch (error) {
      console.error("Error deleting category:", error.response?.data || error.message);
    }
  };
};
