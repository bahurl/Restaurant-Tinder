import React from "react";
import ViewRestaurants from '../ViewRestaurant/ViewRestaurant';
import {baseUrl} from '../../Shared/baseUrl'
import './Vote.css'
import axios from "axios";


function Vote(props) {  
    const[invite, setInvite] = React.useState({})
    const[validLink, setValidLink] = React.useState()
    const[restaurants, setRestaurants] = React.useState([])  

    React.useEffect(() => {
            const getInvite = async () => {
                await axios.get(baseUrl + `/invite/${props.id}`) //needs to throw an error
                    .then((response) => {
                        setInvite(response.data);
                        setValidLink(true);
                    })
                    .catch((error) => {
                        setValidLink(false);
                    });
            };
            getInvite();
            
            const getRestaurants = async () => {
                const restaurant = await axios.get(baseUrl+`/restaurant/search?location=${invite.zipCode !== null ? invite.zipCode : invite.city}`);
                setRestaurants(restaurant.data);
            }
            getRestaurants();
    }, [validLink]);

    let restaurantDisplay = restaurants.map(item =>{  
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
        <div className="restaurant--details">
            <h4 className="restaurant--name">{item.name}</h4>
            <p className="restaurant--type information">{item.type}</p>
            <p className="restaurant--address1 information">{item.address1}</p>
            {item.address2 && <p className="restaurant--address2">{item.address2}</p>}
            {item.address3 && <p className="restaurant--address3">{item.address3}</p>}
            <p className="restaurant--city information">{item.city}</p>
            <p className="restaurant--state information">{item.state}</p>
            <p className="restaurant--zipcode information">{item.zipCode}</p>
            <p className="restaurant--HoO information">Hours of Operation</p>
            <p className="hour--of--operations information">{`${item.openHour}-${item.closeHour}`}</p>
            {isopen ? <p className="restaurant--open information">Open</p>:<p className="restaurant--closed">Closed</p>}
        </div>
    )})
    

    return(
        <div>
            {validLink ? 
                <div>
                    <p>Vote for your favorite restaurant </p>
                    {restaurantDisplay}
                </div>
            :
                <h1>Invalid Invitation Link</h1>
            }
        </div>
    );
}

export default Vote;
