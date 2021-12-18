import React, {Component} from 'react';
import {Button, Container} from "reactstrap";
import AppNavbar from "../util/AppNavBar";
import Module from "./Module";

class ProductDetails extends Component {

    emptyProduct = {
        name: [],
        modules: []
    }

    constructor(props) {
        super(props);
        this.state = {product: this.emptyProduct};
    }

    async componentDidMount() {
        const fetchedDetails = await (await fetch(`/api/products/` + this.props.match.params.id)).json();
        this.setState({product: fetchedDetails});
    }

    render() {
        const {product} = this.state;
        const modules = product.modules;

        const modulesList = modules
            .map(module =>
                        <Module data={module}/>
            );

        return (
            <div>
                <AppNavbar/>

                <Container fluid>
                    <div style={{float: 'right'}}>
                        <Button color="success">Order now</Button>
                    </div>
                    <h3>
                        {this.state.product.name}
                    </h3>
                    <p>
                        Description: bla bla bla
                    </p>
                    <h4>Available Options:</h4>
                    {modulesList}
                </Container>
            </div>
        )
    }
}

export default ProductDetails;