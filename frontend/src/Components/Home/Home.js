import {Link} from 'react-router-dom';
import {useState} from 'react';
import axios from 'axios';
import { Button } from 'bootstrap';

function Home(props) {
    const [location, setLocation] = useState('');
    const [restaurantData, setRestaurantData] = useState([]);
    function handleInput(event){
        setLocation(event.target.value)
    }
   function searchRestaurants(){
    const config = {
        headers: {
          Authorization:
            "Bearer 2aXqt6Jb7FzlweC3h63usgLu5pjld-HL9LPgl8v1hWh6kawrAKtYlz50UZ_R9cN6OE7FYfsNXFcWs15JjC0MDUWsopmaRnMtvW2GvrH6alQ1qAb-brUlNqt8Iv5jY3Yx",
        },
        params: {
          term: "restaurants",
          location: location,
          radius: 1609,
          sort_by: "relevance",
          limit: 50,
        },
      };
      axios
    .get("https://cors-anywhere.herokuapp.com/https://api.yelp.com/v3/businesses/search", config)
    .then((response) => {
      console.log(response); 
    });
    }
    const restaurants = restaurantData.map(item => (
        <div>
            <h2>{item.name}</h2>
            <p>{item.categories[0].title}</p>
        </div>
    ))

    return(
        <div>
            <label for='zipcode' >Enter Zip Code</label>
            <input type='number' placeholder='Zip' name='zipCode' id='zip-input' onChange={handleInput}  />
            <button onClick={searchRestaurants}> search </button>
            {restaurants}
        </div>
    );
}

export default Home;