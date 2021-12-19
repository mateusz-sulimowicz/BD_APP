import React, {Component} from 'react';
import {Button} from "reactstrap";
import {Link} from "react-router-dom";

class Order extends Component {

    render() {
        const id = this.props.data.id;
        const productId = this.props.data.product.id;
        const productName = this.props.data.product.name;


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
                        <Link to={"/products/"+ productId}>
                            {productName}
                        </Link>
                    </Button>
                </td>


            </tr>
        )
    }
}

export default Order;
