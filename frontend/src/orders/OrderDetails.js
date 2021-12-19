import React, {Component} from 'react';
import {Button, Container} from "reactstrap";
import AppNavbar from "../util/AppNavBar";

class OrderDetails extends Component {

    emptyOrder = {
        deadline: {},
        orderDate: {},
        product: {}
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

        console.log(order);

        return (
            <div>
                <AppNavbar/>

                <Container fluid>
                    <h3>

                    </h3>
                    <p>
                        Description: bla bla bla
                    </p>

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
