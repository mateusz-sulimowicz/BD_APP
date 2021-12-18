import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavBar from '../util/AppNavBar';
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
            .then(productsFetchedData => this.setState({products: productsFetchedData}))
    }

    /* async remove(id) {
         await fetch('/api/products/${id}', {
             method: 'DELETE',
             headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
             }
         }).then(() => {
             let updatedProducts = [...this.state.products]
                 .filter(product => product.id !== id);
             this.setState({products: updatedProducts})
         });
     }*/

    render() {
        const {products, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        const productsList = products.map(product => <Product key={product.id} data={product}/>);

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <h3>Products</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="40%">Order</th>
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