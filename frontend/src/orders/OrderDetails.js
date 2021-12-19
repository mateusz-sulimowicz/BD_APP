import React, {Component} from 'react';
import {Button, Container} from "reactstrap";
import AppNavbar from "../util/AppNavBar";
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
                <AppNavbar/>

                <Container fluid>
                    <h1>
                        Order number: {order.id}
                    </h1>
                    <h2>
                        Product: {order.product.name}
                    </h2>
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
                <AppNavbar/>

                <Container fluid>
                    <div style={{float: 'right'}}>
                        <Button color="success">OrderListRecord now</Button>
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
