import React, { useState } from 'react'
import { FaThumbsUp, FaThumbsDown } from "react-icons/fa";


export default function ThumbsUpDown(props){
    const restaurantId = props.restaurantId
    const inviteId = props.inviteId

    const [countUp, setCountUp] = useState(0)
    const [countDown, setCountDown] = useState(0)
    const [thumbsUp, setThumbsUp] = useState(false)
    const [thumbsDown, setThumbsDown] = useState(false)

    

    const handleUpClick = event => {
        if(!thumbsUp && thumbsDown){
            setCountUp(countUp + 1)
            setCountDown(countDown - 1)
            setThumbsUp(true)
            setThumbsDown(false)
        }
        else if(!thumbsDown && thumbsUp){
            setThumbsUp(false)
            setCountUp(countUp - 1)
        }
        else if(!thumbsUp && !thumbsDown){
            setCountUp(countUp + 1)
            setThumbsUp(true)
        }
      }
    const handleDownClick = event => {
        if(!thumbsDown && thumbsUp){
            setCountDown(countDown + 1)
            setCountUp(countUp - 1)
            setThumbsDown(true)
            setThumbsUp(false)
        }
        else if(!thumbsUp && thumbsDown){
            setThumbsDown(false)
            setCountDown(countDown - 1)
        }
        else if(!thumbsDown && !thumbsUp){
            setCountDown(countDown + 1)
            setThumbsDown(true)
        }
    }  

    return(
        <div>
            <button className='thumbs-button' onClick={handleUpClick}><FaThumbsUp />{`${countUp === 0 ? '' : countUp}`}</button>
            <button className='thumbs-button' onClick={handleDownClick}><FaThumbsDown />{`${countDown === 0 ? '' : countDown}`}</button>
        </div>
    )
}