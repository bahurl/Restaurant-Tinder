import Accordion from 'react-bootstrap/Accordion';
import React from 'react';
import ThumbsUpDown from '../../ThumbsUpDown/ThumbsUpDown';


export default function RestaurantCards(props){
   const item = props.data
   const isOpen = props.data.isOpen
    return(
        <div className='restaurant-card' key={props.key}>
            <h3 className='restaurant-type'>{item.name}</h3>
            <div className='img-container'>
            <img className='restaurant-img' src={item.image_url} /> 
            </div>
            <div className='restaurant-info'>
                <h6 className='restaurant-name'>{item.type}</h6>
                <p>{`${item.address1}`}</p>
                {item.address2!==null && (<p>{item.address2}</p>)}
                {item.address3!==null && (<p>{item.address3}</p>)}
                <p>{item.city} {item.state}, {item.zip_code}</p>
            </div>
            <div className='tel-div'>
                {item.phone && <a className='phone' href ={`tel:${item.phone}`}>Call to order</a>}            
            </div>
            
            <div className='isOpen-div'>
                {isOpen ? (<h3 className='isOpen'>Open</h3>) : (<h3 className='isOpen'>Closed</h3>) }
            </div>
            <div className='ThumbsUpDown'>
             {props.isVote && <ThumbsUpDown restaurantId={props.data.restaurantId} inviteId={props.inviteId}/>}
            </div>
        </div>
    )
}