import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import {
  Button,
  TextField,
  Typography,
  CssBaseline,
  Container,
  MenuItem,
  FormControl,
  InputLabel,
  Select,
  Snackbar,
  Alert,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { registerUser } from "../../../State/Authentication/Action";

const initialValues = {
  fullName: "",
  email: "",
  password: "",
  role: "ROLE_CUSTOMER",
};

const validationSchema = Yup.object({
  fullName: Yup.string().required("Full Name is required"),
  email: Yup.string()
    .email("Invalid email format")
    .required("Email is required"),
  password: Yup.string()
    .min(6, "Password must be at least 6 characters")
    .required("Password is required"),
  role: Yup.string().required("Role is required"),
});

const RegistrationForm = () => {
  const [openSnackBar, setOpenSnackBar] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = (values, { resetForm }) => {
    dispatch(registerUser({ userData: values, navigate }));
    setOpenSnackBar(true);
    resetForm(); // Reset form after successful submission
  };

  const handleCloseSnackBar = () => {
    setOpenSnackBar(false);
    navigate("/account/login");
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div>
        <Typography variant="h5" align="center">
          Register
        </Typography>
        
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={handleSubmit}
        >
          {({ errors, touched, handleChange, values }) => (
            <Form>
              <Field
                as={TextField}
                label="Full Name"
                name="fullName"
                fullWidth
                margin="normal"
                variant="outlined"
                error={touched.fullName && Boolean(errors.fullName)}
                helperText={<ErrorMessage name="fullName" />}
              />

              <Field
                as={TextField}
                label="Email Address"
                name="email"
                type="email"
                fullWidth
                margin="normal"
                variant="outlined"
                error={touched.email && Boolean(errors.email)}
                helperText={<ErrorMessage name="email" />}
              />

              <Field
                as={TextField}
                label="Password"
                name="password"
                type="password"
                fullWidth
                margin="normal"
                variant="outlined"
                error={touched.password && Boolean(errors.password)}
                helperText={<ErrorMessage name="password" />}
              />

              <FormControl
                fullWidth
                margin="normal"
                variant="outlined"
                error={touched.role && Boolean(errors.role)}
              >
                <InputLabel>Role</InputLabel>
                <Select
                  name="role"
                  value={values.role}
                  onChange={handleChange}
                  label="Role"
                >
                  <MenuItem value="ROLE_CUSTOMER">Customer</MenuItem>
                  <MenuItem value="ROLE_RESTAURANT_OWNER">Restaurant Owner</MenuItem>
                </Select>
                <ErrorMessage
                  name="role"
                  component="div"
                  style={{ color: "red", fontSize: "0.75rem" }}
                />
              </FormControl>

              <Button type="submit" fullWidth variant="contained" color="primary" sx={{ mt: 3 }}>
                Register
              </Button>
            </Form>
          )}
        </Formik>

        <Typography variant="body2" align="center" sx={{ mt: 3 }}>
          Already have an account?{" "}
          <Button onClick={() => navigate("/account/login")}>Login</Button>
        </Typography>
      </div>

      <Snackbar
        open={openSnackBar}
        autoHideDuration={3000}
        onClose={handleCloseSnackBar}
        anchorOrigin={{ vertical: "top", horizontal: "center" }}
      >
        <Alert onClose={handleCloseSnackBar} severity="success" sx={{ width: "100%" }}>
          Registration Successful!
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default RegistrationForm;
