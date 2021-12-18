import React, {Component} from 'react';
import {Button} from "reactstrap";
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
            </tr>
        )
    }
}

export default Product
