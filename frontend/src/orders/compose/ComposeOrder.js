import React, {Component} from 'react';
import {Button} from "reactstrap";
import {Link} from "react-router-dom";
import SelectOption from "./SelectOption";

class ComposeOrder extends Component {

    emptyProduct = {
        name: [],
        modules: []
    }

    constructor(props, context) {
        super(props, context);

        this.state = {product: this.emptyProduct};
    }

    componentDidMount() {
        fetch(`/api/products/` + this.props.match.params.id)
            .then(response => response.json())
            .then(fetchedDetails => this.setState({product: fetchedDetails}));
    }

    render() {
        const {product} = this.state;

        const selectOptionList = product.modules.map(module => <SelectOption data={module}/>)

        return (
            <div>
                <h1>
                    {product.name}
                </h1>
                {selectOptionList}
            </div>
        )
    }



}

export default ComposeOrder;