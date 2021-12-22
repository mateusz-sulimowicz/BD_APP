import React, {Component} from 'react';
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class Product extends Component {

    render() {
        const id = this.props.data.id;

        return (
            <tr>
                <td>
                    <Button color="link">
                        <Link to={"/products/details/" + id}>
                            {this.props.data.name}
                        </Link>
                    </Button>
                </td>
                <td>
                    {this.props.data.basePrice}
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={`/products/${id}`}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.props.onRemoved(id)}>Delete</Button>
                    </ButtonGroup>
                </td>

            </tr>
        )
    }
}

export default Product
