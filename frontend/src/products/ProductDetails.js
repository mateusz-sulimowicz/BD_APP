import React, {Component} from 'react';
import {Button, Container} from "reactstrap";
import AppNavBar from "../util/AppNavBar";
import Module from "./Module";
import {Link} from "react-router-dom";

class ProductDetails extends Component {

    emptyProduct = {
        name: [],
        modules: []
    }

    constructor(props) {
        super(props);
        this.state = {product: this.emptyProduct};
    }

    componentDidMount() {
        fetch(`/api/products/` + this.props.match.params.id)
            .then(response => response.json())
            .then(fetchedDetails => this.setState({product: fetchedDetails}));
    }

    render() {
        const {product} = this.state;
        const modules = product.modules;

        const modulesList = modules
            .map(module => <Module data={module}/>);
        return (
            <div>
                

                <Container fluid>
                    <div style={{float: 'right'}}>
                        <Button color="success">
                            <Link to={`/products/composeOrder/${product.id}`}>
                                Order now
                            </Link>
                        </Button>

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