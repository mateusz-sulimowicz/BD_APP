import React, {Component} from 'react';

class Product extends Component {

    render() {
        return (
            <tr>
                <td>{this.props.data.name}</td>
            </tr>
        )
    }

}

export default Product
