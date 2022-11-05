import axios from 'axios'
import {Component} from 'react'
import {Link} from 'react-router-dom'
import { baseUrl } from '../../Shared/baseUrl'
import './Register.css'

class Register extends Component{

    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: '',
            confirmPassword: ''
        }
        
    }

    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    function validEmail() {
       
        const atSign = email.indexOf("@")
        const period = email.indexOf(".")
        if((atSign < period) && (atSign != -1) && (period != -1)) {
            return true;
        } else {
            return false;
        } 
    }

    function validPassword(pass) {
        if(pass.length < 8) {
            return false
        } 
        
        let cap = "[A-Z]".test(pass)
        let lower = "[a-z]".test(pass)
        let num = [0-9].test(pass)

        return cap && lower && num;
    }

    handleSubmit = () => {
        
        if(!validEmail(this.state.username)) {
            alert("Invalid email")
            return;
        }

        if(!validPassword(this.state.password)) {
            alert("Invalid password")
            return;
        }
        
        const data = {username: this.state.username, password: this.state.password, confirmPassword: this.state.confirmPassword, role: 'USER'}
        
        // if(this.state.password === this.state.confirmPassword){
        //     axios.post(baseUrl + "/register", data)
        // }else{
        //     alert("Password and Confirm Password must match!!!")
        // }

        if(this.state.password === this.state.confirmPassword){
            
            const response = axios.post(baseUrl + "/register", data);
            if(response.status != 200) {
                alert("Email address is already in use.")
            }

        }else{
            alert("Password and Confirm Password must match!!!")
        }
    }

    render(){
        return(
            <div className='register'>
                <h1>Create Account</h1>
                <label class="sr-only">Username</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    class="form-control"
                    placeholder="Username"
                    v-model="user.username"
                    onChange={this.handleInputChange}
                    required
                />
                <label class="sr-only">Password</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    class="form-control"
                    placeholder="Password"
                    v-model="user.password"
                    onChange={this.handleInputChange}
                    required
                />
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
                <Link to="/login">Have an account?</Link>
                <button type="submit" onClick={this.handleSubmit}>Sign up</button>
            </div>
        )
    }
}

export default Register;