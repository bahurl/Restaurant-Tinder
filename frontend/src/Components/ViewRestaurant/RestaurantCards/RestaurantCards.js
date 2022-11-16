import Accordion from 'react-bootstrap/Accordion';
import React from 'react';


export default function RestaurantCards(props){
   const item = props.data
   const isOpen= props.data.isOpen
    return(
        <div className='restaurant-card'>
            <h3 className='restaurant-type'>{item.type}</h3>
            <div className='img-container'>
            <img className='restaurant-img' src={item.image_url} /> 
            </div>
            <div className='restaurant-info'>
                <h2 className='restaurant-name'>{item.name}</h2>
                <h4>{`${item.address1}`}</h4>
                {item.address2!==null && (<h4>{item.address2}</h4>)}
                {item.address3!==null && (<h4>{item.address3}</h4>)}
            </div>
            <div className='tel-div'>
                {item.phone && <a className='phone' href ={`tel:${item.phone}`}>Call to order</a>}            
            </div>
            
            <div className='isOpen-div'>
                {isOpen ? (<h3 className='isOpen'>Open</h3>) : (<h3 className='isOpen'>closed</h3>) }
            </div>
        </div>
    )
}