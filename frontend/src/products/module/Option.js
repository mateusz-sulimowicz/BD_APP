import React, {Component} from 'react';
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class Option extends Component {

    render() {
        console.log(this.props)
        const id = this.props.data.item.id;
        const name = this.props.data.item.name;
        const price = this.props.data.price;

        return (
            <tr key={id}>
                <td>
                    {id}
                </td>
                <td>
                    {name}
                </td>
                <td>
                    {price}
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

export default Option;
