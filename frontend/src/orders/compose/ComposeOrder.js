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

        this.state = {
            canSubmit: false,
            product: this.emptyProduct,
            selectedOptions: {},
            totalPrice: 0
        };
    }

    componentDidMount() {
        fetch(`/api/products/` + this.props.match.params.id)
            .then(response => response.json())
            .then(fetchedDetails => this.setState({
                product: fetchedDetails,
                totalPrice: fetchedDetails.basePrice
            }));
    }

    onOptionSelected(option) {
        const moduleId = option.moduleId;

        let newSelectedOptions = this.state.selectedOptions;
        newSelectedOptions[moduleId] = {
            item: option.item,
            price: option.price
        }

        this.setState({selectedOptions: newSelectedOptions});

        const options = this.state.selectedOptions;
        let allOptionsFilledIn = true;
        let price = this.state.product.basePrice;
        this.state.product.modules.forEach(function (module) {
            if (options[module.id] === undefined) {
                allOptionsFilledIn = false;
            } else {
                price += options[module.id].price;
            }
        })

        this.setState({canSubmit: allOptionsFilledIn, totalPrice: price});
    }

    async onOrderSubmitted() {
        let calculatedValue = this.state.product.basePrice;
        console.log("TYLE POLICZYLEM!: " + calculatedValue)

        const options = this.state.selectedOptions;
        this.state.product.modules.forEach(function (module) {
            calculatedValue += options[module.id].price;
        })

        console.log("TYLE POLICZYLEM!: " + calculatedValue)
        let order = {
            value: calculatedValue,
            product: this.state.product,
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
        }).then(response => response.json())
            .then(createdOrder => orderId = createdOrder.id)


        console.log("ZWROCONO: " + orderId);

        console.log(this.state.selectedOptions);

        for (const module of this.state.product.modules) {
            await fetch('/api/orderConfigs', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({itemId: options[module.id].item.id, moduleId: module.id, orderId}),
            })
        }

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
                <h2>
                    Total price: {this.state.totalPrice}
                </h2>
                {selectOptionList}
                <Button onClick={() => this.onOrderSubmitted()} disabled={!this.state.canSubmit}>
                    Submit
                </Button>
                <Button color="secondary" tag={Link} to="/products">Cancel</Button>
            </div>

        )
    }


}

export default ComposeOrder;