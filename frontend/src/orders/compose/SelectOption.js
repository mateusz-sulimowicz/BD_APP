import React, {Component, Fragment} from 'react';
import Select from 'react-select';

class SelectOption extends Component {

    emptyModule = {
        items: []
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
    }

    render() {
        const items = this.state.module.items;

        const itemOptions = items.map(item => {
                return {
                    value: item.id,
                    label: item.name
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

        return (
            <Fragment>
                {this.state.module.name}
                {selectOption}
            </Fragment>
        );
    }
}

export default SelectOption;
