import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getRestaurantsEvents } from '../../../State/Customers/Restaurant/restaurant.action'; // Correct import
import EventCard from '../../../Admin/Events/EventCard';

const CustomerEvents = () => {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");

  const { restaurant, auth } = useSelector(store => store);

  useEffect(() => {
    if (restaurant?.usersRestaurant?.id) {
      dispatch(getRestaurantsEvents({
        restaurantId: restaurant.usersRestaurant.id,
        jwt: auth.jwt || jwt
      }));
    }
  }, [restaurant?.usersRestaurant?.id, dispatch, auth.jwt, jwt]);

  return (
    <div className="mt-5 px-5 flex flex-wrap gap-5">
      {restaurant?.restaurantsEvents?.map((item) => (
        <div key={item.id}>
          <EventCard isCustomer={true} item={item} />
        </div>
      ))}
    </div>
  );
};

export default CustomerEvents;
