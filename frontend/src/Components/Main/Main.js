import {Component} from 'react'
import {Switch, Route, Redirect, Link} from 'react-router-dom'
import Login from '../login-register/Login/Login'
import Register from '../login-register/Register/Register'
import Home from '../Home/Home'
import Invite from '../Invite/Invite'
import {addToken, deleteUser} from '../../Redux/actionCreators'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import './main.css'

const mapStateToProps = state => {
    return {
        token: state.token,
        user: state.user
    }
}

const mapDispatchToProps = (dispatch) => ({
    addToken: () => { dispatch(addToken()) },
    deleteUser: () => { dispatch(deleteUser())}
});

class Main extends Component {
    constructor(props){
        super(props);
    }

    handleLogout = () => {
        this.props.addToken("")
        this.props.deleteUser()
    }

    render(){
        return(
            <div>
                {this.props.token.token !== undefined &&
                        <div className='nav'>
                            <Link className='link' to='/home'>Home</Link>
                            <Link className='link' to='/invite'>Invite</Link>
                            <Link className='link' to='/login' onClick={this.handleLogout}>logout</Link> 
                            <Redirect to='/home'/>
                        </div>  
                }
                <Switch>
                    <Route path='/login' component={() => <Login/>}/>
                    <Route path='/register'component={() => <Register/>}/>
                    <Route path='/invite'component={this.props.token.token !== undefined ? () => <Invite user={this.props.user}/> : null}/>
                    <Route path='/home/:route' component={() => <Home/>}/>
                    <Route path='/home' component={this.props.token.token !== undefined ? () => <Home/> : null}/>
                    <Redirect to='/login'/>
                </Switch>
            </div>
        )
    }
} 

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Main));