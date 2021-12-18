import React, {Component} from 'react';
import {Navbar, Nav, NavbarBrand, NavLink} from 'reactstrap';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <Navbar  color="dark" dark expand="md">
            <NavbarBrand href="/home">Home</NavbarBrand>
            <Nav className="me-auto">
                <NavLink href="/products">Products</NavLink>
                <NavLink href="/orders">Orders</NavLink>

            </Nav>
        </Navbar>;
    }
}