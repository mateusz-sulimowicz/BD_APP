import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class ProductEdit extends Component {

    emptyProduct = {
        name: '',
        basePrice: 0,
        modules: []
    };

    constructor(props) {
        super(props);
        this.state = {
            product: this.emptyProduct
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const fetchedProduct = await (await fetch(`/api/products/${this.props.match.params.id}`)).json();
            this.setState({product: fetchedProduct});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let product = {...this.state.product};
        product[name] = value;
        this.setState({product});
        console.log(this.state.item)
    }

    async handleSubmit(event) {
        event.preventDefault();
        const product = this.state.product;

        console.log(product);

        await fetch('/api/products' + (this.props.match.params.id !== 'new' ? '/' + product.id : ''), {
            method: this.props.match.params.id !== 'new' ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product),
        });
        this.props.history.push('/products');
    }

    render() {
        const {product} = this.state;
        const title = <h2>{this.props.match.params.id !== 'new' ? 'Edit Product' : 'Add Product'}</h2>;

        return <div>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={product.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/products">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}

export default ProductEdit;