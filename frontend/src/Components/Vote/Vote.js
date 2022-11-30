import React from "react";
import ViewRestaurants from '../ViewRestaurant/ViewRestaurant';
import {baseUrl} from '../../Shared/baseUrl'
import RestaurantCards from "../ViewRestaurant/RestaurantCards/RestaurantCards";
import './Vote.css'
import axios from "axios";
import { object } from "prop-types";


function Vote(props) {  
    const[invite, setInvite] = React.useState({})
    const[validLink, setValidLink] = React.useState()
    const[restaurants, setRestaurants] = React.useState([])  
    const[restaurantIds, setRestaurantIds] = React.useState([])
    const[vote, setVote] = React.useState([])
    const[isVote, setIsVote] = React.useState()
    

    

    React.useEffect(() => {

        //Checking to see if link has expired
        // const currentTimeDate = new Date();
        // let inviteDate='';
        // const getInviteDate = async () => {
        //     const req = await axios.get(baseUrl + `/invite/${props.id}`)
        //     const res = await req
        //     console.log(res.data.invitationDate)
        //     inviteDate = new Date(res.data.invitationDate)
        //     console.log(inviteDate)
        //     console.log(currentTimeDate)
        //     if (currentTimeDate> inviteDate){
        //         return false
        //     } else{
        //         return true
        //     }
        // }
        // getInviteDate()

        const getData = async () => {
            try{
                const req = await axios.get(baseUrl + `/data/${props.id}`)
            const res = await req
            setInvite(res.data.invite)
            console.log(res.data.invite)
            setRestaurantIds(res.data.restaurantIds)
            // console.log(res.data.restaurantIds)
            setVote(res.data.vote)
            // console.log(res.data.vote)
            setRestaurants(res.data.restaurants)
            // console.log(res.data.restaurants)
            setIsVote(res.data.expired)
            setValidLink(true)
            } catch(error){
                setValidLink(false)
            }
            
        }
        getData();
            // const getInvite = async () => {
            //     await axios.get(baseUrl + `/invite/${props.id}`) //needs to throw an error
            //         .then((response) => {
            //             console.log(response)
            //             setInvite(response.data);
            //             setValidLink(true);
            //         })
            //         .catch((error) => {
            //             setValidLink(false);
            //         });
            // };
            // getInvite();
            
            // const getRestaurants = async () => {
            //     const restaurant = await axios.get(baseUrl+`/restaurant/search?location=${invite.zipCode !== null ? invite.zipCode : invite.city}`)
            //     setRestaurants(await restaurant.data);
            //     console.log(restaurant)
            // }
            // getRestaurants();
            // setTimeout(, 5000);
            
    }, []);
    // React.useEffect(getIds(),[restaurants])
    // React.useEffect(getVotes(),[restaurantIds])

    // React.useEffect(
    //     saveVotes()
    // ,[vote])

    function getIds(){
            setRestaurantIds(restaurants.map(item =>{
                let id = item.restaurantId
            return id;
        }))  
    }

    async function getVotes(){
        const invitationId = invite.invitationId;
       console.log(restaurantIds)
        let config = {
            body: {restaurantIds:restaurantIds, invitationId:invitationId}
        }
 
        
        let data = axios.get(baseUrl + '/votes/vote', config)
          if(data.length <0){
            for( let i = 0; i< restaurantIds.length ; i++){
                let restaurantId = restaurantIds[i];
                config = {
                    body: {restaurantId:restaurantId,invitationId: invitationId, thumbsUp:0, thumbsDown:0}
                }
                axios.post(baseUrl + '/votes/create', config); 
            }
            config = {
                body: {restaurantIds:[...restaurantIds], invitationId:invitationId}
            }
            data = axios.get(baseUrl + '/votes/vote', config)
            console.log(data)
          }
            setVote(data.data)
    }
    
    async function saveVotes(singleVote){
        const resId = singleVote.restaurantId
        const inId = singleVote.invitationId
        const thumbsUp = singleVote.thumbsUp
        const thumbsDown = singleVote.thumbsDown
        if(typeof(singleVote) != "undefined"){
            const config = {
                restaurantId: resId, invitationId: inId, thumbsUp:thumbsUp, thumbsDown:thumbsDown
            }
            console.log(config)
           const req =  await axios.put(baseUrl + '/votes/save', config);
           const res = await req
           console.log(res.data)
        
        }
        
    }
     
    function updateVote( restaurantId, countUp, countDown,){
        const newVotes = vote.map(item => {
            if(item.restaurantId === restaurantId ){
                
                return {...item, thumbsUp:item.thumbsUp+countUp, thumbsDown:item.thumbsDown+countDown}
            }
            return item;
        })
        setVote(newVotes)
        console.log(newVotes)
        newVotes.forEach(item => {
            if(item.restaurantId === restaurantId ){  
                console.log(item)  
                saveVotes(item)
            }
        })
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
            <div key={item.restaurantId}>
                <RestaurantCards isVote={isVote} data={item} isOpen={isOpen} updateVote={updateVote}
            restaurantId={item.restaurantId} inviteId={props.inviteId}/>
            </div>
            

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
    

    return(
        <div>
            {validLink ? 
                <div>
                    <p className="header">{isVote ? "Vote for your favorite restaurant" : "Finalist"} </p>
                    {restaurantDisplay}
                </div>
            :
                <h1>Invalid Invitation Link</h1>
            }
        </div>
    );
}

export default Vote;
