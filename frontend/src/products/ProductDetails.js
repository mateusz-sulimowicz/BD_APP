import React, {Component} from 'react';
import {Button, Container} from "reactstrap";
import AppNavBar from "../util/AppNavBar";
import Module from "./module/Module";
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
        if (this.props.match.params.id !== 'new') {
            fetch(`/api/products/` + this.props.match.params.id)
                .then(response => response.json())
                .then(fetchedDetails => this.setState({product: fetchedDetails}));
        }
    }

    async remove(id) {
        await fetch(`/api/modules/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => {
            console.log(response)
            if (response.ok) {
                let updatedModules = [...this.state.product.modules].filter(i => i.id !== id);
                let product = this.state.product;
                product.modules = updatedModules;
                this.setState({product: product});
            }
        });
    }


    render() {

        console.log(this.props)

        const {product} = this.state;
        const modules = product.modules;

        const modifiable = this.props.match.params.action === 'setup'

        const modulesList = modules
            .map(module => <Module modifiable={modifiable} onRemoved={(id) => this.remove(id)}
                                   data={module}/>);
        return (
            <div>

                <Container fluid>
                    <div style={{float: 'right'}}>
                        {
                            !modifiable &&
                            <Button color="success"
                                    tag={Link}
                                    to={`/products/composeOrder/${product.id}`}>
                                Add new order
                            </Button>
                        }
                    </div>
                    <h3>
                        Product: {this.state.product.name}
                    </h3>
                    <h3>
                        Base price: {this.state.product.basePrice}
                    </h3>
                    {
                        modifiable &&
                        <Button color="primary"
                                tag={Link}
                                to={`/modules/${product.id}/new/`}>
                            Add Module
                        </Button>
                    }
                    <h4>Available Options:</h4>
                    {modulesList}
                </Container>
            </div>
        )
    }
}

export default ProductDetails;