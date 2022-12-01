import axios from "axios";
import React from "react";
import { useState } from "react";
import {baseUrl} from '../../Shared/baseUrl'
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
            const openHour = item.timesList[0].open
            const closeHour = item.timesList[0].close
            const d = new Date();
            const currentHour = d.getHours();
            if (openHour <= currentHour &&  closeHour >= currentHour  ){
                return true;
            } else {
                return false;
            }  
        }
        const open = isOpen()
        return(
            <RestaurantCards isVote={false} data={item} isOpen={open} key={item.restaurant_id}/>
    )})

    return (
        <div className="location--list">
            <div className="search--bar">
                <label id="location-label" >Location</label>
                <input className='input' id="location" name="location" type="text" placeholder="Enter City or Zip Code" onChange={handleInput} />
                <button onClick={getRestaurants} id="search-btn" className="search--button">Search</button>
            </div>
            <div className="list-cards">
               {isSearch && restaurantDisplay}
            </div>
        </div>    
    )
}

