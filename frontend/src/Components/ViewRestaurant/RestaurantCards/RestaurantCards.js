import Accordion from 'react-bootstrap/Accordion';
import React from 'react';
import ThumbsUpDown from '../../ThumbsUpDown/ThumbsUpDown';


export default function RestaurantCards(props){
   const item = props.data
   const isOpen = props.data.open
   let timeOpen1 = () => {
    if (item.timesList[0].open > 12 ){
        const open = item.timesList[0].open -12
        return `${open}pm`
    } else if (item.timesList[0].open = 12 ){
        const open = item.timesList[0].open
        return `${open}pm`
    } else if(item.timesList[0].open = 24){
        const open = item.timesList[0].open -12
        return `${open}am` 
    } else {
        const open = item.timesList[0].open
        return `${open}am`
    }
   }
   let timeClose1 =() => {
    if (item.timesList[0].close > 12 ){
        const close = item.timesList[0].close -12
        return `${close}pm`
    } else if (item.timesList[0].close = 12 ){
        const close = item.timesList[0].close
        return `${close}pm`
    } else if(item.timesList[0].close = 24){
        const close = item.timesList[0].close -12
        return `${close}am` 
    } else {
        const close = item.timesList[0].close
        return `${close}am`
    }
   }
   let timeOpen2 = () => {
    if(item.timesList.length > 1){
        if (item.timesList[1].open > 12 ){
            const open = item.timesList[1].open -12
            return `${open}pm`
        } else if (item.timesList[1].open = 12 ){
            const open = item.timesList[1].open
            return `${open}pm`
        } else if(item.timesList[1].open = 24){
            const open = item.timesList[1].open -12
            return `${open}am` 
        } else {
            const open = item.timesList[1].open
            return `${open}am`
        }
    } else {
        return 0
    }
   }
   let timeClose2 = () => {
    if(item.timesList.length > 1){
        if (item.timesList[1].close > 12 ){
            const close = item.timesList[1].close -12
            return `${close}pm`
        } else if (item.timesList[1].close = 12 ){
            const close = item.timesList[1].close
            return `${close}pm`
        } else if(item.timesList[1].close = 24){
            const close = item.timesList[1].close -12
            return `${close}am` 
        } else {
            const close = item.timesList[1].close
            return `${close}am`
        }
    } else{
        return 0
    }
   }

   
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
                <p>{item.timesList[0].dayFrom} - {item.timesList[0].dayTo} {timeOpen1()} - {timeClose1()}</p>
                {item.timesList.length > 1 && (<p>{item.timesList[1].dayFrom} - {item.timesList[1].dayTo} {timeOpen2()} - {timeClose2()}</p>)}
            </div>
            <div className='tel-div'>
                {item.phone && <a className='phone' href ={`tel:${item.phone}`}>Call to order</a>}            
            </div>
            
            <div className='isOpen-div'>
                {isOpen ? (<h3 className='isOpen'>Open</h3>) : (<h3 className='isOpen'>Closed</h3>) }
            </div>
            <div className='ThumbsUpDown'>
             {props.isVote && <ThumbsUpDown updateVote={props.updateVote} restaurantId={props.data.restaurantId} inviteId={props.inviteId}/>}
            </div>
        </div>
    )
}