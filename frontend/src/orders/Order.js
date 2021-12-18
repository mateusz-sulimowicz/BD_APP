import React, {Component} from 'react';
import {Button} from "reactstrap";
import {Link} from "react-router-dom";

class Order extends Component {

    render() {
        const id = this.props.data.id;

        return (
            <tr>
                <td>
                    <Button color="link">
                        <Link to={"/orders/"+ id}>
                            {this.props.data.name}
                        </Link>
                    </Button>
                </td>
            </tr>
        )
    }
}

export default Order;
