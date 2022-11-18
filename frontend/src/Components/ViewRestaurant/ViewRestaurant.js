import axios from "axios";
import React from "react";
import { useState , useEffect} from "react";
import {baseUrl} from '../../Shared/baseUrl'
import { Token } from "../../Redux/token";
import './ViewRestaurant.css'
import RestaurantCards from "./RestaurantCards/RestaurantCards";


export default function ViewRestaurants(){
    const [restaurants, setRestaurants] = useState([]);
    const [input, setInput] = useState({location:"", type: ""});
    const [isSearch, setIsSearch] = useState(false)
    async function getRestaurants(){

        const data = await axios.get(baseUrl+`/restaurant/search?location=${input.location}`)
        setRestaurants(data.data);
        setIsSearch(true)

    }

    function handleInput(event){
        const value = event.target.value;
        const name = event.target.name;
        setInput(perv => ({...perv, [name]: value}));
    }

    const restaurantDisplay = restaurants.map(item =>{  
        const isOpen = () => { 
            const openHour = item.openHour.split(":");
            const closeHour = item.closeHour.split(":");
            const d = new Date();
            const currentHour = d.getHours();
            const currentMinutes = d.getMinutes();
            if (openHour[0] <= currentHour && openHour[1] <= currentMinutes && closeHour[0] >= currentHour && closeHour[1] > currentMinutes ){
                return true;
            } else {
                return false;
            }
        }
        return(
            <RestaurantCards data={item} isOpen={isOpen} key={item.restaurant_id}/>
    )})

    return (

        <div className="location--list">
            <div className="search--bar">
                <label id="location-label" >Location</label>
                <input id="location" name="location" type="text" placeholder="Enter City or Zip Code" onChange={handleInput} />
                <button onClick={getRestaurants} className="search--button">Search</button>
            </div>
            <div className="list-cards">
               {isSearch && restaurantDisplay}
            </div>
        </div>    
            

        
        

    )
}

