import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class ItemEdit extends Component {

    emptyItem = {
        name: '',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const fetchedItem = await (await fetch(`/api/items/${this.props.match.params.id}`)).json();
            this.setState({item: fetchedItem});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
        console.log(this.state.item)
    }

    async handleSubmit(event) {
        event.preventDefault();
        const item = this.state.item;

        console.log(item);

        await fetch('/api/items' + (this.props.match.params.id !== 'new' ? '/' + item.id : ''), {
            method: this.props.match.params.id !== 'new' ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then(response => {
            if (response.ok) {
                this.props.history.push('/items');
            } else {
                this.setState({errorMessage: "Item already exists!"});
            }
        });
    }

    render() {
        const {item} = this.state;
        const title = <h2>{this.props.match.params.id !== 'new' ? 'Edit Item' : 'Add Item'}</h2>;

        return <div>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                               onChange={this.handleChange} autoComplete="basePrice"/>
                    </FormGroup>
                    <FormGroup>
                        <Button disabled={this.state.item.name === ''}
                                color="primary"
                                type="submit">
                            Save
                        </Button>
                        <Button color="secondary" tag={Link} to="/items">Cancel</Button>
                    </FormGroup>
                </Form>
                {this.state.errorMessage}
            </Container>
        </div>
    }

}

export default ItemEdit;