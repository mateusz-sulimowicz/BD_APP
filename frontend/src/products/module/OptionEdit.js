import React, { Component } from 'react';
import { Link, withRouter, useLocation } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class OptionEdit extends Component {

    constructor(props) {
        super(props);

        let option = {
            productId: this.props.match.params.productId,
            moduleId: this.props.match.params.moduleId,
        }

        this.state = {option: option};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let option = {...this.state.option};
        option[name] = value;
        this.setState({option});
        console.log(this.state.option)
    }

    async handleSubmit(event) {
        event.preventDefault();
        const option = this.state.option;

        console.log(this.state);

        console.log(this.props);

        await fetch(`/api/options`, {
            method: this.props.match.params.id !== 'new' ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({price: option.price, moduleId: option.moduleId, item: {name: option.itemName}}),
        }).then(response => {
            console.log(response);
            if (!response.ok) {
                this.setState({errorMessage: `Submition failed!`})
            } else {
                this.setState({errorMessage: undefined});
            }
        });
        if (this.state.errorMessage === undefined) {
            this.props.history.push(`/products/details/${option.productId}`)
        }
    }

    render() {
        const {option, errorMessage} = this.state;
        const title = <h2>{this.props.match.params.id !== 'new' ? 'Edit Option' : 'Add Option'}</h2>;

        return <div>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="price">Price</Label>
                        <Input type="number" name="price" id="price" value={option.price || ''}
                               onChange={this.handleChange} autoComplete="price"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="Item name">Item Name</Label>
                        <Input type="string" name="itemName" id="itemName" value={option.itemName || ''}
                               onChange={this.handleChange} autoComplete="itemName"/>
                    </FormGroup>
                    <FormGroup>
                        <Button disabled={this.state.option.price === undefined || this.state.option.price <= 0 || this.state.option.itemName === undefined}
                                color="primary"
                                type="submit">
                            Save
                        </Button>
                        <Button color="secondary" tag={Link} to={`/products/details/${option.productId}`}>Cancel</Button>
                    </FormGroup>
                </Form>
                {errorMessage}
            </Container>
        </div>
    }

}

export default OptionEdit