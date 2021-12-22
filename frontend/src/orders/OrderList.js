import React, {Component} from 'react';
import {Container, Table} from "reactstrap";
import Order from "./Order"

class OrderList extends Component {

    constructor(props) {
        super(props);
        this.state = {orders: []};
    }

    componentDidMount() {
        fetch('/api/orders')
            .then(response => response.json())
            .then(fetchedOrders =>  this.setState({orders: fetchedOrders}));

    }

    async remove(id) {
        await fetch(`/api/orders/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => {
            console.log(response)
            if (response.ok) {
                let updatedOrders = [...this.state.orders].filter(i => i.id !== id);
                this.setState({orders: updatedOrders});
            }
        });
    }

    render() {
        const orders = this.state.orders;

        console.log(this.state.orders);
        const orderList = orders
            .map(order =>
                <Order key={order.id} onRemoved={(id) => this.remove(id)} data={order}/>);

        return (
            <div>
                
                <Container fluid>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="5%">id</th>
                            <th width="20%">product</th>
                            <th width="5%">quantity</th>
                            <th width="5%">value</th>
                            <th width="10%">order date</th>
                        {/*    <th width="10%">deadline</th>*/}
                        </tr>
                        </thead>
                        <tbody>
                        {orderList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default OrderList;