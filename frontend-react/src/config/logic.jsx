export const isPresentInFavorites = (favorites, restaurant) => {
  if (!Array.isArray(favorites)) {
    console.error("Favorites is not an array:", favorites);
    return false;
  }
  
  // Iterate over the favorites array
  for (let item of favorites) {
    if (restaurant.id === item.id) return true;
  }
  return false;
};
