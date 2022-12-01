import React from "react";
import {baseUrl} from '../../Shared/baseUrl'
import RestaurantCards from "../ViewRestaurant/RestaurantCards/RestaurantCards";
import './Vote.css'
import axios from "axios";


function Vote(props) {  
    const[invite, setInvite] = React.useState({})
    const[validLink, setValidLink] = React.useState()
    const[restaurants, setRestaurants] = React.useState([])  
    const[restaurantIds, setRestaurantIds] = React.useState([])
    const[vote, setVote] = React.useState([])
    const[isVote, setIsVote] = React.useState()
    

    

    React.useEffect(() => {
        const getData = async () => {
            try{
                const req = await axios.get(baseUrl + `/data/${props.id}`)
            const res = await req
            setInvite(res.data.invite)
            setRestaurantIds(res.data.restaurantIds)
            setVote(res.data.vote)
            setRestaurants(res.data.restaurants)
            setIsVote(res.data.expired)
            setValidLink(true)
            } catch(error){
                setValidLink(false)
            }
            
        }
        getData();            
    }, []);



    
    async function saveVotes(singleVote){
        const resId = singleVote.restaurantId
        const inId = singleVote.invitationId
        const thumbsUp = singleVote.thumbsUp
        const thumbsDown = singleVote.thumbsDown
        if(typeof(singleVote) != "undefined"){
            const config = {
                restaurantId: resId, invitationId: inId, thumbsUp:thumbsUp, thumbsDown:thumbsDown
            }
            await axios.put(baseUrl + '/votes/save', config);

        
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
        newVotes.forEach(item => {
            if(item.restaurantId === restaurantId ){  
                saveVotes(item)
            }
        })
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
            <div key={item.restaurantId}>
                <RestaurantCards isVote={!isVote} data={item} isOpen={open} updateVote={updateVote}
            restaurantId={item.restaurantId} inviteId={props.inviteId}/>
            </div>
    )})
    

    return(
        <div>
            {validLink ? 
                <div>
                    <p className="header">{!isVote ? "Vote for your favorite restaurant" : "Finalist"} </p>
                    {restaurantDisplay}
                </div>
            :
                <h1>Invalid Invitation Link</h1>
            }
        </div>
    );
}

export default Vote;
