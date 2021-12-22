import React, {Component} from 'react';
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class Order extends Component {

    render() {
        console.log(this.props)
        const id = this.props.data.id;
        const productId = this.props.data.product.id;
        const productName = this.props.data.product.name;
        const quantity = this.props.data.quantity;
        const orderDate = this.props.data.orderDate;
        const value = this.props.data.value;

        return (
            <tr>
                <td>
                    <Button color="link">
                        <Link to={"/orders/"+ id}>
                            {id}
                        </Link>
                    </Button>
                </td>
                <td>
                    <Button color="link">
                        <Link to={"/products/details/" + productId}>
                            {productName}
                        </Link>
                    </Button>
                </td>
                <td>
                    {quantity}
                </td>
                <td>
                    {value}
                </td>
                <td>
                    {orderDate}
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="danger" onClick={() => this.props.onRemoved(id)}>Delete</Button>
                    </ButtonGroup>
                </td>

            </tr>
        )
    }
}

export default Order;
