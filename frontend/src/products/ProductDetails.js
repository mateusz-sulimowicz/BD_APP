import React, {Component} from 'react';
import {Button, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";
import AppNavbar from "../util/AppNavBar";
import Product from "./Product";
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

        console.log(product.modules);

        const modules = this.state.product.modules;
        const modulesList = modules.map(module => <Module key={module.id} data={module}/>);

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
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Modules</th>
                        </tr>
                        </thead>
                        <tbody>
                        {modulesList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default ProductDetails;