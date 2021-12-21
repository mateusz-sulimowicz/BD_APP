import React, {Component} from 'react';
import {Button, Container, Table} from "reactstrap";
import PickedOption from "./PickedOption";

class OrderDetails extends Component {

    emptyOrder = {
        product: {
            modules: []
        },
    }

    constructor(props) {
        super(props);
        this.state = {order: this.emptyOrder};
    }

    componentDidMount() {
        fetch(`/api/orders/` + this.props.match.params.id)
            .then(response => response.json())
            .then(fetchedDetails => this.setState({order: fetchedDetails}));
    }

    render() {
        const {order} = this.state;

        const pickedOptions = order.product.modules
            .map(module => <PickedOption
                key={module.id}
                module={module}
                orderId={order.id}/>
            )

        console.log(pickedOptions);

        return (
            <div>
                

                <Container fluid>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%"> Order details</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                Order number: {order.id}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Product: {order.product.name}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Unit price: {order.value}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quantity: {order.quantity}
                            </td>
                        </tr>
                        </tbody>
                    </Table>
                    {pickedOptions}
                </Container>
            </div>
        )

        /*const modulesList = modules
            .map(module =>
                <Module data={module}/>
            );



        return (
            <div>
                

                <Container fluid>
                    <div style={{float: 'right'}}>
                        <Button color="success">Order now</Button>
                    </div>
                    <h3>
                        {this.state.product.name}
                    </h3>
                    <p>
                        Description: bla bla bla
                    </p>
                    <h4>Available Options:</h4>
                    {modulesList}
                </Container>
            </div>
        )*/
    }

}

export default OrderDetails;
