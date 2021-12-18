import React, { Component } from 'react';
import '../App.css';
import AppNavbar from '../util/AppNavBar';
import {Container } from 'reactstrap';

class Home extends Component {

    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                </Container>
            </div>
        );
    }
}
export default Home;