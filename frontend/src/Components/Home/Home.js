import {Link} from 'react-router-dom';
import {useState} from 'react';
import axios from 'axios';
import { Button } from 'bootstrap';
import React from 'react';
import ViewRestaurants from '../ViewRestaurant/ViewRestaurant';

function Home(props) {


    return(
        <div>
            <p>if you can see this your logged in</p>
            <ViewRestaurants />
        </div>
    );
}

export default Home;