import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";
import Item from "./Item";

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
        }).then(response => {
            console.log(response)
            if (response.ok) {
                let updatedItems = [...this.state.items].filter(i => i.id !== id);
                this.setState({items: updatedItems});
            }
        });
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
            .map(item => <Item data={item} onRemoved={(id) => this.remove(id)}/>);

        return (
            <div>
                <Container fluid>
                    <div style={{float: 'right'}}>
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