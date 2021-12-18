import React, {Component} from 'react';
import {Container, Table} from "reactstrap";
import AppNavBar from "../util/AppNavBar";
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

    render() {
        const orders = this.state.orders;

        const orderList = orders
            .map(order => <Order key={order.id} data={order}/>);

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Orders</th>
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