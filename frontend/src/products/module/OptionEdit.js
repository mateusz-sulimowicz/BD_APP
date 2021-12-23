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

    async componentDidMount() {
        /*if (this.props.match.params.id !== 'new') {
            const fetchedItem = await (await fetch(`/api/options/${this.props.match.params.id}`)).json();
            this.setState({item: fetchedItem});
        }*/
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
            body: JSON.stringify({price: option.price, moduleId: option.moduleId, item: {id: option.itemId}}),
        }).then(response => console.log(response));
        this.props.history.push(`/products/details/${option.productId}`);
    }

    render() {
        const {option} = this.state;
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
                        <Label for="itemId">Item ID</Label>
                        <Input type="number" name="itemId" id="itemId" value={option.itemId || ''}
                               onChange={this.handleChange} autoComplete="itemId"/>
                    </FormGroup>
                    <FormGroup>
                        <Button disabled={this.state.option.price === undefined || this.state.option.itemId === undefined}
                                color="primary"
                                type="submit">
                            Save
                        </Button>
                        <Button color="secondary" tag={Link} to={`/products/details/${option.productId}`}>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}

export default OptionEdit