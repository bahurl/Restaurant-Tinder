import axios from 'axios';
import React from 'react';
import { User } from '../../Redux/user';
import { baseUrl } from '../../Shared/baseUrl';
import {nanoid} from "nanoid"
import './Invite.css';


function Invite(props) {

    const [formData, setFormData] = React.useState(
        {
            location: "",
            datetime: ""
        }
    )

    const [displayInvite, setDisplayInvite] = React.useState(false);
    const [inviteLink, setInviteLink] = React.useState("www.google.com");

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
        //alert(`${props.user.id} ${formData.location} ${formData.datetime} ${nanoid()}`)

        const isNum = /^\d+$/.test(formData.location);
        const inviteId = nanoid();
        
        const invite = {
            ownerId: props.user.id,
            [isNum ? 'zipCode' : 'city']: formData.location,
            invitationDate: formData.datetime,
            invitationLink: inviteId
        } 
        
        axios.post(baseUrl + "/invite/create", invite)
                .then((response) => {
                    setInviteLink(`http://localhost:3000/vote/${inviteId}`)
                    setDisplayInvite(oldInvite => !oldInvite);
                })
                .catch((error) => {
                    alert(error);
                });
    }

    return(
        <div className='invite'>
            <h1 className='title'>Invite your friends out to eat.</h1>

            {displayInvite ? 
            <div className='invite'>
                <h2>Invitation Link</h2> 
                <input type="text" value={inviteLink}></input>
                <button className='s' onClick={() => setDisplayInvite(oldInvite => !oldInvite)}>{"Invite Another Friend"}</button>      
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
                <button className='submit' type="submit" onClick={handleSubmit}>Invite</button>      
            </div>
            }
        </div>
    );
}

export default Invite;