import axios from 'axios';
import React from 'react';
import './Invite.css'

function Invite(props) {

    const [formData, setFormData] = React.useState(
        {
            location: "",
            date: ""
        }
    )

    function handleInputChange(event) {
        event.preventDefault()
        setFormData(prevFormData => {
            return {
                ...prevFormData,
                [event.target.name]: event.target.value
            }
            
        })
    }

    function handleSubmit() {
    }

    return(
        <div className='invite'>
            <h1 className='title'>Invite your friends out to eat.</h1>

            <label className='location'>Restaurant location</label>
                <input
                    className='location-input'
                    type="text"
                    id="location"
                    name="location"
                    placeholder="location or zip code"
                    value={formData.location}
                    onChange={handleInputChange}
                    required
                />

            <label className='date-label'>Decision date and time</label>
                <input
                    className='date-input'
                    type="date"
                    id="date"
                    name="date"
                    value={formData.date}
                    onChange={handleInputChange}
                    required
                />
        
            <button className='submit' type="submit" onClick={handleSubmit}>Invite</button>      
        </div>
    );
}

export default Invite;