import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getRestaurantById, getRestaurantsCategory } from "../../../State/Customers/Restaurant/restaurant.action";
import { getMenuItemsByRestaurantId } from "../../../State/Customers/Menu/menu.action";
import { Typography, Divider, Grid, Snackbar, Alert, Backdrop, CircularProgress } from "@mui/material";
import MenuItemCard from "../../components/MenuItem/MenuItemCard";

const Restaurant = () => {
  const dispatch = useDispatch();
  const { id } = useParams();
  const { restaurant, menu } = useSelector((store) => store);
  const navigate = useNavigate();
  const location = useLocation();
  const jwt = localStorage.getItem("jwt");
  const [snackbar, setSnackbar] = useState({ open: false, message: "" });

  useEffect(() => {
    if (jwt) {
      dispatch(getRestaurantById({ jwt, restaurantId: id }));
      dispatch(
        getMenuItemsByRestaurantId({
          jwt,
          restaurantId: id,
          foodCategory: new URLSearchParams(location.search).get("food_category") || "",
        })
      );
      dispatch(getRestaurantsCategory({ jwt, restaurantId: id }));
    }
  }, [dispatch, id, jwt, location.search]);

  return (
    <>
      <div className="px-5 lg:px-20">
        <Typography variant="h4">{restaurant.restaurant?.name || "Loading..."}</Typography>
        <Divider />
        <Grid container spacing={2}>
          {menu.menuItems?.length ? (
            menu.menuItems.map((item) => (
              <MenuItemCard key={item.id} item={item} onAddToCart={() => setSnackbar({ open: true, message: "Item added!" })} />
            ))
          ) : (
            <Typography variant="h6">No menu items found.</Typography>
          )}
        </Grid>
      </div>

      <Snackbar open={snackbar.open} autoHideDuration={3000} onClose={() => setSnackbar({ open: false, message: "" })}>
        <Alert severity="success">{snackbar.message}</Alert>
      </Snackbar>

      <Backdrop open={menu.loading || restaurant.loading}>
        <CircularProgress />
      </Backdrop>
    </>
  );
};

export default Restaurant;