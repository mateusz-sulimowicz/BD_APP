import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";

class ItemList extends Component {

    constructor(props) {
        super(props);
        this.state = {items: []};
    }

    async remove(id) {
        await fetch(`/api/items/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedItems = [...this.state.items].filter(i => i.id !== id);
            this.setState({items: updatedItems});
        });
        this.props.history.push('/items');
    }

    componentDidMount() {
        fetch('/api/items')
            .then(response => response.json())
            .then(fetchedOrders =>  this.setState({items: fetchedOrders}));
    }

    render() {
        const items = this.state.items;

        console.log(this.state.items);
        const itemList = items
            .map(item =>
                <tr key={item.id}>
                    <td>
                        {item.id}
                    </td>
                    <td>
                        {item.name}
                    </td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={`/items/${item.id}`}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.props.remove(item.id)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>);

        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/items/new">Add Item</Button>
                    </div>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="5%">id</th>
                            <th width="20%">name</th>
                            <th width="40%">actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {itemList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default ItemList;