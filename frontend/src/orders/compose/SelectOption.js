import React, {Component, Fragment} from 'react';
import Select from 'react-select';

class SelectOption extends Component {

    emptyModule = {
        options: []
    }

    constructor(props) {
        super(props);
        this.state = {
            module: this.emptyModule,
            selectedOption: {}
        };
    }

    componentDidMount() {
        fetch(`/api/modules/` + this.props.data.id)
            .then(response => response.json())
            .then(fetchedModule => this.setState({module: fetchedModule}));
    }

    handleChange = (selectedOption) => {
        this.setState({selectedOption: selectedOption});
        const id = this.state.module.id;
        this.props.onChange({moduleId: id, item: selectedOption.value, price: selectedOption.price});
    }

    render() {
        const options = this.state.module.options;

        const itemOptions = options.map(option => {
                return {
                    value: option.item,
                    label: option.item.name + " +" + option.price,
                    price: option.price
                }
            }
        )

        const selectOption = <Select
            className="basic-single"
            classNamePrefix="select"
            name="color"
            options={itemOptions}
            onChange={this.handleChange}
        />

        console.log(this.state)

        return (
            <Fragment>
                {this.state.module.name}
                {selectOption}
            </Fragment>
        );
    }
}

export default SelectOption;
