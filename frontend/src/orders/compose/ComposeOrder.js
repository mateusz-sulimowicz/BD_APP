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

        this.state = {canSubmit: false, product: this.emptyProduct, selectedOptions: {}};
    }

    onOptionSelected(option) {
        const moduleId = option.moduleId;
        const selectedVal = option.itemId;
        const price = option.price;

        let newSelectedOptions = this.state.selectedOptions;
        newSelectedOptions[moduleId] = {
            itemId: option.itemId,
            price: option.price
        }

        this.setState({selectedOptions: newSelectedOptions});

        const options = this.state.selectedOptions;
        let allOptionsFilledIn = true;
        this.state.product.modules.forEach(function (module) {
            if (options[module.id] === undefined) {
                allOptionsFilledIn = false;
            }
        })
        this.setState({canSubmit: allOptionsFilledIn});
    }

    async onOrderSubmitted() {
        let order = {
            value: 2137,
            product:  this.state.product,
            quantity: 1
        }

        console.log(JSON.stringify(order));

        let orderId;

        await fetch('/api/orders', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order),
        })
            .then(response => response.json())
            .then(createdOrder => orderId = createdOrder.id)

        console.log("ZWROCONO: " + orderId);

        /* await fetch('/clients' + (item.id ? '/' + item.id : ''), {
             method: (item.id) ? 'PUT' : 'POST',
             headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
             },
             body: JSON.stringify(item),
         });
         this.props.history.push('/clients');


     console.log("NEEEEW")*/
    }

    componentDidMount() {
        fetch(`/api/products/` + this.props.match.params.id)
            .then(response => response.json())
            .then(fetchedDetails => this.setState({product: fetchedDetails}));
    }

    render() {
        const {product} = this.state;

        const selectOptionList = product.modules.map(module =>
            <SelectOption onChange={(opt) => this.onOptionSelected(opt)} data={module}/>)

        console.log(this.state)
        return (
            <div>
                <h1>
                    {product.name}
                </h1>
                {selectOptionList}
                <Button onClick={() => this.onOrderSubmitted()} disabled={!this.state.canSubmit}>
                    Submit
                </Button>
            </div>

        )
    }


}

export default ComposeOrder;