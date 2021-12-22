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
                        <Link to={"/products/"+ id}>
                            {this.props.data.name}
                        </Link>
                    </Button>
                </td>
                <ButtonGroup>
                    <Button size="sm" color="danger" onClick={() => this.props.onRemoved(id)}>Delete</Button>
                </ButtonGroup>
            </tr>
        )
    }
}

export default Product
