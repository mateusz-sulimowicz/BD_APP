import React, {Component} from 'react';

class Module extends Component {

    render() {
        return (
            <tr>
                <td>
                    {this.props.data.name}
                </td>
            </tr>
        )
    }

}

export default Module;