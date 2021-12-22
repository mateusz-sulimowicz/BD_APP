import React, {Component} from 'react';
import {Container, Table} from 'reactstrap';
import Product from "./Product"

class ProductList extends Component {

    constructor(props) {
        super(props);
        this.state = {products: []};
        /* this.remove = this.remove.bind(this);*/
        // You must bind functions to class instance, to allow proper `this.` binding
    }

    componentDidMount() {
        fetch('/api/products')
            .then(response => response.json())
            .then(fetchedProducts =>  this.setState({products: fetchedProducts}));
    }

    render() {
        const products = this.state.products;

        const productsList = products
            .map(product => <Product key={product.id} data={product}/>);

        return (
            <div>
                
                <Container fluid>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Please select a product.</th>
                        </tr>
                        </thead>
                        <tbody>
                        {productsList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }

}

export default ProductList;