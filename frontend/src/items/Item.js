import React, {Component} from 'react';
import {Button} from "reactstrap";
import {Link} from "react-router-dom";

class Item extends Component {

    render() {
        console.log(this.props)
        const id = this.props.data.id;
        const name = this.props.data.name;

        return (
            <tr>
                <td>
                    {id}
                </td>
                <td>
                    {name}
                </td>
            </tr>
        )
    }
}

export default Item;
