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
            <RestaurantCards data={item} isOpen={isOpen}/>

        // <div className="restaurant--details">
            
        //     <h4 className="restaurant--name">{item.name}</h4>
        //     <p className="restaurant--type information">{item.type}</p>
        //     <p className="restaurant--address1 information">{item.address1}</p>
        //     {item.address2 && <p className="restaurant--address2">{item.address2}</p>}
        //     {item.address3 && <p className="restaurant--address3">{item.address3}</p>}
        //     <p className="restaurant--city information">{item.city}</p>
        //     <p className="restaurant--state information">{item.state}</p>
        //     <p className="restaurant--zipcode information">{item.zipCode}</p>
        //     <p className="restaurant--HoO information">Hours of Operation</p>
        //     <p className="hour--of--operations information">{`${item.openHour}-${item.closeHour}`}</p>
        //     {isopen ? <p className="restaurant--open information">Open</p>:<p className="restaurant--closed">Closed</p>}
        // </div>
    )})

    return (
        <div className="location--list">
            <div className="search--bar">
                <label id="location-label" >Location</label>
                <input id="location" name="location" type="text" placeholder="Enter City or Zip Code" onChange={handleInput} />
                <button onClick={getRestaurants} className="search--button">Search</button>
            </div>
            
            {isSearch && restaurantDisplay}
        </div>

    )
}

