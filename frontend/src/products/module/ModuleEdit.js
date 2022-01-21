import React, { Component } from 'react';
import { Link, withRouter, useLocation } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class ModuleEdit extends Component {

    emptyModule = {
        name: '',
    };

    constructor(props) {
        super(props);

        let module = {
            name: '',
            productId: this.props.match.params.productId
        }

        this.state = {module: module};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        /*if (this.props.match.params.id !== 'new') {
            const fetchedItem = await (await fetch(`/api/modules/${this.props.match.params.id}`)).json();
            this.setState({item: fetchedItem});
        }*/
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let module = {...this.state.module};
        module[name] = value;
        this.setState({module});
        console.log(this.state.module)
    }


    async handleSubmit(event) {
        event.preventDefault();
        const module = this.state.module;

        console.log(this.state);

        console.log(this.props);

        await fetch('/api/modules' + (this.props.match.params.id !== 'new' ? '/' + module.id : ''), {
            method: this.props.match.params.id !== 'new' ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(module),
        }).then(response => console.log(response));
        this.props.history.push(`/products/details/${module.productId}/setup`);
    }

    render() {
        const {module} = this.state;
        const title = <h2>{this.props.match.params.id !== 'new' ? 'Edit Module' : 'Add Module'}</h2>;

        return <div>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={module.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Button disabled={this.state.module.name === ''}
                                color="primary"
                                type="submit">
                            Save
                        </Button>
                        <Button color="secondary" tag={Link} to={`/products/details/${module.productId}/setup`}>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}

export default ModuleEdit