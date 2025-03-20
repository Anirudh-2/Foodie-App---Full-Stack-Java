import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { createEventAction, getRestaurantsEvents, deleteEventAction } from "../../State/Customers/Restaurant/restaurant.action";
import { Box, Button, Grid, Modal, TextField, Snackbar, Alert } from "@mui/material";
import EventCard from "./EventCard";
import { useFormik } from "formik";
import * as Yup from "yup";
import { DateTimePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

const style = { position: "absolute", top: "50%", left: "50%", transform: "translate(-50%, -50%)", width: 400, bgcolor: "background.paper", p: 4 };

const initialValues = { image: "", location: "", name: "", startedAt: null, endsAt: null };
const validationSchema = Yup.object({
  image: Yup.string().required("Image URL is required"),
  location: Yup.string().required("Location is required"),
  name: Yup.string().required("Event name is required"),
  startedAt: Yup.date().required("Start date required"),
  endsAt: Yup.date().min(Yup.ref("startedAt"), "End must be after start").required("End date required"),
});

const Events = () => {
  const [openModal, setOpenModal] = useState(false);
  const [snackBarMessage, setSnackBarMessage] = useState("");
  const [openSnackBar, setOpenSnackBar] = useState(false);
  
  const dispatch = useDispatch();
  const { restaurant, auth } = useSelector((state) => state);
  const jwt = auth.jwt || localStorage.getItem("jwt");

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit: (values) => {
      dispatch(createEventAction({
        data: values,
        restaurantId: restaurant.usersRestaurant?.id,
        jwt,
      }));
      setSnackBarMessage("Event Created!");
      setOpenSnackBar(true);
      formik.resetForm();
      setOpenModal(false);
    },
  });

  useEffect(() => {
    if (restaurant.usersRestaurant) {
      dispatch(getRestaurantsEvents({
        restaurantId: restaurant.usersRestaurant.id,
        jwt,
      }));
    }
  }, [restaurant.usersRestaurant, dispatch, jwt]);

  const handleDelete = (eventId) => {
    dispatch(deleteEventAction(eventId, jwt));
    setSnackBarMessage("Event Deleted!");
    setOpenSnackBar(true);
  };

  return (
    <div>
      <Button onClick={() => setOpenModal(true)}>Create Event</Button>

      <Grid container spacing={2}>
        {restaurant.restaurantsEvents?.map((event) => (
          <EventCard key={event.id} item={event} onDelete={() => handleDelete(event.id)} />
        ))}
      </Grid>

      <Modal open={openModal} onClose={() => setOpenModal(false)}>
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
            <TextField label="Image URL" name="image" fullWidth {...formik.getFieldProps("image")} />
            <TextField label="Location" name="location" fullWidth {...formik.getFieldProps("location")} />
            <TextField label="Event Name" name="name" fullWidth {...formik.getFieldProps("name")} />
            
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DateTimePicker label="Start" value={formik.values.startedAt} onChange={(value) => formik.setFieldValue("startedAt", value)} />
              <DateTimePicker label="End" value={formik.values.endsAt} onChange={(value) => formik.setFieldValue("endsAt", value)} />
            </LocalizationProvider>

            <Button type="submit">Submit</Button>
          </form>
        </Box>
      </Modal>

      <Snackbar open={openSnackBar} onClose={() => setOpenSnackBar(false)}>
        <Alert severity="success">{snackBarMessage}</Alert>
      </Snackbar>
    </div>
  );
};

export default Events;
