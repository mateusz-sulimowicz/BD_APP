import React, {Component} from 'react';
import {Table} from "reactstrap";

class Module extends Component {

    emptyModule = {
        items: []
    }

    constructor(props) {
        super(props);
        this.state = {module: this.emptyModule};
    }

    componentDidMount() {
        fetch(`/api/modules/` + this.props.data.id)
            .then(response => response.json())
            .then(fetchedModule => this.setState({module: fetchedModule}));
    }

    render() {
        const items = this.state.module.items;
        const itemList = items.map(item =>
            <tr key={item.id}>
                <td>{item.name}</td>
            </tr>
        )

        return (
            <Table className="mt-4">
                <thead>
                <tr>
                    <th width="30%"> {this.props.data.name}</th>
                </tr>
                </thead>
                <tbody>
                {itemList}
                </tbody>
            </Table>
        )
    }

}

export default Module;