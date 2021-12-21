import React, {Component} from 'react';
import {Table} from "reactstrap";

class Module extends Component {

    emptyModule = {
        options: []
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
        const options = this.state.module.options;
        const optionList = options.map(option =>
            <tr key={option.item.id}>
                <td>{option.item.name}</td>
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
                {optionList}
                </tbody>
            </Table>
        )
    }

}

export default Module;