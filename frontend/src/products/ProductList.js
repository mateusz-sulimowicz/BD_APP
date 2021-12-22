import React, {Component} from 'react';
import {Button, Container, Table} from 'reactstrap';
import Product from "./Product"
import {Link} from "react-router-dom";

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

    async remove(id) {
        await fetch(`/api/products/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => {
            console.log(response)
            if (response.ok) {
                let updatedProducts = [...this.state.products].filter(i => i.id !== id);
                this.setState({products: updatedProducts});
            }
        });
    }

    render() {
        const products = this.state.products;

        const productsList = products
            .map(product => <Product key={product.id} onRemoved={(id) => this.remove(id)} data={product}/>);

        return (
            <div>
                
                <Container fluid>
                    <div style={{float: 'right'}}>
                        <Button color="success" tag={Link} to="/products/new">Add Product</Button>
                    </div>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">Product</th>
                            <th width="5%">Price</th>
                            <th width="5%">Actions</th>
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