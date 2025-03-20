import {
  Badge,
  Button,
  Card,
  CardContent,
  CardHeader,
  Grid,
} from "@mui/material";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import InstagramIcon from "@mui/icons-material/Instagram";
import TwitterIcon from "@mui/icons-material/Twitter";
import LinkedInIcon from "@mui/icons-material/LinkedIn";
import FacebookIcon from "@mui/icons-material/Facebook";
import {
  updateRestaurant,
  updateRestaurantStatus,
} from "../../State/Customers/Restaurant/restaurant.action";

const Details = () => {
  const dispatch = useDispatch();
  const { auth, restaurant, ingredients } = useSelector((store) => store);
  const jwt = localStorage.getItem("jwt");

  const handleRestaurantStatus = (restaurantId, jwt, status) => {
    // Ensure status is passed here, otherwise the function will throw the "Status is required" error
    if (!status) {
      console.error("Missing status for restaurant update!");
      return;
    }

    dispatch(updateRestaurantStatus({ restaurantId, jwt, status }));
  };

  // Destructuring userRestaurant safely with default values
  const userRestaurant = restaurant.usersRestaurant || {};

  return (
    <div className="lg:px-20 px-5">
      <div className="py-5 flex justify-center items-center gap-5">
        <h1 className="text-2xl lg:text-7xl text-center font-bold p-5">
          {userRestaurant?.name || "Restaurant Name"}
        </h1>
        <div>
          <Button
            // Pass the required arguments here
            onClick={() =>
              handleRestaurantStatus(
                userRestaurant?._id, // Assuming the restaurant's ID is stored in _id
                jwt,
                !userRestaurant?.open ? "open" : "close" // Toggle status
              )
            }
            size="large"
            className="py-[1rem] px-[2rem]"
            variant="contained"
            color={userRestaurant?.open ? "error" : "primary"}
          >
            {userRestaurant?.open ? "Close" : "Open"}
          </Button>
        </div>
      </div>

      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Card>
            <CardHeader title={<span className="text-gray-300">Restaurant</span>} />
            <CardContent>
              <div className="space-y-4 text-gray-200">
                <div className="flex">
                  <p className="w-48">Owner</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.owner?.fullName || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Restaurant Name</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.name || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Cuisine Type</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.cuisineType || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Opening Hours</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.openingHours || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Status</p>
                  <div className="text-gray-400">
                    <span className="pr-5">-</span>
                    {userRestaurant?.open ? (
                      <span className="px-5 py-2 rounded-full bg-green-400 text-gray-950">
                        Open
                      </span>
                    ) : (
                      <span className="text-black px-5 py-2 rounded-full bg-red-400">
                        Closed
                      </span>
                    )}
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        </Grid>

        {/* Address Section */}
        <Grid item xs={12} lg={5}>
          <Card>
            <CardHeader title={<span className="text-gray-300">Address</span>} />
            <CardContent>
              <div className="space-y-3 text-gray-200">
                <div className="flex">
                  <p className="w-48">Country</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.address?.country || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">City</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.address?.city || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Postal Code</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.address?.postalCode || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Street Address</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.address?.streetAddress || "Not Available"}
                  </p>
                </div>
              </div>
            </CardContent>
          </Card>
        </Grid>

        {/* Contact Section */}
        <Grid item xs={12} lg={7}>
          <Card>
            <CardHeader title={<span className="text-gray-300">Contact</span>} />
            <CardContent>
              <div className="space-y-3 text-gray-200">
                <div className="flex">
                  <p className="w-48">Email</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {userRestaurant?.contactInformation?.email || "Not Available"}
                  </p>
                </div>
                <div className="flex">
                  <p className="w-48">Mobile</p>
                  <p className="text-gray-400">
                    <span className="pr-5">-</span> {" +91"} {userRestaurant?.contactInformation?.mobile || "Not Available"}
                  </p>
                </div>
                <div className="flex items-center">
                  <p className="w-48">Social</p>
                  <div className="text-gray-400 flex items-center pb-3">
                    <span className="pr-5">-</span>
                    <a
                      target="_blank"
                      href={userRestaurant?.contactInformation?.instagram || "#"}
                      rel="noreferrer"
                    >
                      <InstagramIcon sx={{ fontSize: "3rem" }} />
                    </a>
                    <a
                      className="ml-5"
                      href={userRestaurant?.contactInformation?.twitter || "#"}
                      target="_blank"
                      rel="noreferrer"
                    >
                      <TwitterIcon sx={{ fontSize: "3rem" }} />
                    </a>
                    <a
                      className="ml-5"
                      href={userRestaurant?.contactInformation?.linkedin || "#"}
                      target="_blank"
                      rel="noreferrer"
                    >
                      <LinkedInIcon sx={{ fontSize: "3rem" }} />
                    </a>
                    <a
                      className="ml-5"
                      href={userRestaurant?.contactInformation?.facebook || "#"}
                      target="_blank"
                      rel="noreferrer"
                    >
                      <FacebookIcon sx={{ fontSize: "3rem" }} />
                    </a>
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </div>
  );
};

export default Details;
