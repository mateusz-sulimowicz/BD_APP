import React, {Component} from 'react';
import {Table} from "reactstrap";

class PickedOption extends Component {

    constructor(props) {
        super(props);
        this.state = {item: {}};
    }

    componentDidMount() {
        fetch(`/api/items/orderconfig?orderId=${this.props.orderId}&moduleId=${this.props.module.id}`)
            .then(response => response.json())
            .then(fetchedItem => this.setState({item: fetchedItem}));
    }

    render() {
        const {item} = this.state;

        console.log(this.state.item);

        return (
            <Table className="mt-4">
                <thead>
                <tr>
                    <th width="30%"> {this.props.module.name}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        {item.name}
                    </td>
                </tr>
                </tbody>
            </Table>
        );
    }
}

export default PickedOption;