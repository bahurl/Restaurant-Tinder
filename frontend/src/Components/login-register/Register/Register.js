import axios from 'axios'
import {Component} from 'react'
import {Link} from 'react-router-dom'
import { baseUrl } from '../../../Shared/baseUrl'
import '../login-register.css'

class Register extends Component{

    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: '',
            confirmPassword: '',
            
        }
        
    }

    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    validEmail = (email) => {
        let atCount = 0;
        let periodCount = 0;
        const atSign = this.state.username.indexOf("@")
        const period = this.state.username.indexOf(".")

        for (let i = 0; i < email.length; i++) {
            if(email[i]==='@') {
                atCount++;
                continue;
            } if(email[i]==='.') {
                periodCount++;
                continue;
            }  
        }

        if((atSign < period) && (period-atSign > 1) && (atCount===1) && (periodCount===1)) {
            return true;
        } else {
            return false;
        } 
    }
 
    validPassword = (pass) => {
        if(pass.length < 8) {
            return false;
        } else { 
            let cap = /[A-Z]/g.test(pass)
            let lower = /[a-z]/g.test(pass)
            let num = /[0-9]/g.test(pass)
            return cap && lower && num;
        }
    }

    handleSubmit = () => {
        
        if(!this.validEmail(this.state.username)) {
            alert("Invalid email")
            return;
        }

        if(!this.validPassword(this.state.password)) {
            alert("Invalid password")
            return;
        }
        
        const data = {username: this.state.username, password: this.state.password, confirmPassword: this.state.confirmPassword, role: 'USER'}
        

        if(this.state.password === this.state.confirmPassword){
            
            axios.post(baseUrl + "/register", data)
                .then((response) => {
                    alert("Account successfully created!");
                })
                .catch((error) => {
                    alert("Email address is already in use.");
                });

        }else{
            alert("Password and Confirm Password must match!")
        }
    }

    render(){
        return(
            <div className='register'>
                <h1>Create Account</h1>
                <div className='login-register'>
                    <label id="username--box" class="sr-only">Username</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        class="form-control"
                        placeholder="Username"
                        v-model="user.username"
                        value={this.username}
                        onChange={this.handleInputChange}
                        required
                    />
                    <label id="password--box" class="sr-only">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        class="form-control"
                        placeholder="Password"
                        v-model="user.password"
                        value={this.password}
                        onChange={this.handleInputChange}
                        required
                    />
                    <label id="password--box" class="sr-only">Password</label>
                    <input
                        type="password"
                        id="password-confirm"
                        name="confirmPassword"
                        class="form-control"
                        placeholder="Confirm Password"
                        v-model="user.password"
                        onChange={this.handleInputChange}
                        required
                    />
                    <div id="sign-in--register">
                        <Link id='register-btn' to="/login">Have an account?</Link>
                        <button id='sign-in-btn' type="submit" onClick={this.handleSubmit}>Sign up</button>
                    </div>
                </div>            
            </div>
        )
    }
}

export default Register;