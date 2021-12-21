import React, {Component} from 'react';
import {Container, Table} from "reactstrap";
import Item from "./Item"

class ItemList extends Component {

    constructor(props) {
        super(props);
        this.state = {items: []};
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
                <Item key={item.id} data={item}/>);

        return (
            <div>
                
                <Container fluid>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="5%">id</th>
                            <th width="20%">name</th>
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