import React, {Component} from 'react';
import {Button, Container, Table} from "reactstrap";
import Option from "./Option";
import {Link} from "react-router-dom";

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

    async removeOption(id) {
        await fetch(`/api/options?itemId=${id}&moduleId=${this.state.module.id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => {
            console.log(response)
            if (response.ok) {
                let updatedOptions = [...this.state.module.options].filter(i => i.item.id !== id);
                let module = this.state.module;
                module.options = updatedOptions;
                this.setState({module: module});
            }
        });
    }


    render() {
        const options = this.state.module.options;
        const optionList = options.map(option =>
            <Option onRemoved={(id) => this.removeOption(id)} data={option}/>
        )

        return (
            <div>


            <h3>
                {this.props.data.name}
            </h3>

                <Container fluid>
                    <div style={{float: 'right'}}>
                        <Button color="primary" tag={Link} to="/products/new">Add Option</Button>
                        <Button color="danger" onClick={() => this.props.onRemoved(this.state.module.id)}> Delete </Button>
                    </div>
                    <Table className="mt-4">
                        <thead className="thead-light">
                        <tr>
                            <th width="5%"> Id </th>
                            <th width="15%"> Name </th>
                            <th width="10%"> Price </th>
                            <th width="10%"> Actions </th>
                        </tr>
                        </thead>
                        <tbody>
                        {optionList}
                        </tbody>
                    </Table>
                </Container>


            </div>
        )
    }

}

export default Module;