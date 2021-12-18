import React, {Component} from 'react';

class Product extends Component {

    render() {
        console.log(this)
        return (
            <tr>
                <td>{this.props.data.name}</td>
            </tr>
        )
    }

}

export default Product
