import axios from "axios";
import React from "react";
import { useState , useEffect} from "react";
import {baseUrl} from '../../Shared/baseUrl'
import { Token } from "../../Redux/token";

export default function ViewRestaurants(){
    const [restaurants, setRestaurants] = useState([]);
    const [input, setInput] = useState({location:"", type: ""});
    const [isSearch, setIsSearch] = useState(false)
    async function getRestaurants(){
        // const header = {
        //     params:{location: input.location,
        //             type: input.type} }
        const data = await axios.get(baseUrl+`/restaurant/search?location=${input.location}`)
        // const data = await res.json()
        setRestaurants(data.data);
        setIsSearch(true)
    }

    function handleInput(event){
        const value = event.target.value;
        const name = event.target.name;
        setInput(perv => ({...perv, [name]: value}));
    }

    const restaurantDisplay = restaurants.map(item =>{  
        const isopen = () => { 
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
        <div>
            <h4>{item.name}</h4>
            <p>{item.type}</p>
            <p>{item.address1}</p>
            {item.address2 && <p>{item.address2}</p>}
            {item.address3 && <p>{item.address3}</p>}
            <p>{item.city}</p>
            <p>{item.state}</p>
            <p>{item.zipCode}</p>
            {isopen ? <p>Open</p>:<p>Closed</p>}
        </div>
    )})

    return (
        <div>
            <label id="location-label" for="location">Location</label>
            <input id="location" name="location" type="text" placeholder="Enter City or Zip Code" onChange={handleInput} />
            <button onClick={getRestaurants}>Search</button>
            {isSearch && restaurantDisplay}
        </div>

    )
}

