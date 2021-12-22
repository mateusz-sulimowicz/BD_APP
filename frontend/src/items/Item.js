import React, {Component} from 'react';
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class Item extends Component {

    render() {
        console.log(this.props)
        const id = this.props.data.id;
        const name = this.props.data.name;

        return (
            <tr key={id}>
                <td>
                    {id}
                </td>
                <td>
                    {name}
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={`/items/${id}`}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.props.onRemoved(id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    }
}

export default Item;
