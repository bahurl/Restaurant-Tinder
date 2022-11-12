import axios from 'axios';
import React from 'react';
import { baseUrl } from '../../Shared/baseUrl';
import './Invite.css';


function Invite(props) {

    const [formData, setFormData] = React.useState(
        {
            location: "",
            datetime: ""
        }
    )

    const [inviteLink, setInviteLink] = React.useState(false)

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
         //axios.post(baseUrl + `/invite?location=${formData.location}&dateTime=${formData.datetime}`)
        //         .then((response) => {
        //             alert("Account successfully created!");
        //         })
        //         .catch((error) => {
        //             alert("Email address is already in use.");
        //         });
        setInviteLink(oldInvite => !oldInvite);
    }

    return(
        <div className='invite'>
            <h1 className='title'>Invite your friends out to eat.</h1>

            {inviteLink ? 
            <div>
                <h2>Invitation Link</h2> 
                <input type="text" value="www.google.com"></input>
            </div>
            :  
            <div className='location-datetime'>
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
                <label className='datetime-label'>Decision date and time</label>
                    <input
                        className='datetime-input'
                        type="datetime-local"
                        id="datetime"
                        name="datetime"
                        value={formData.datetime}
                        onChange={handleInputChange}
                        required
                    />
            </div>
            }
        
            <button className='submit' type="submit" onClick={handleSubmit}>{inviteLink ? "Invite Another Friend": "Invite"}</button>      
        </div>
    );
}

export default Invite;