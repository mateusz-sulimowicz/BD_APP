import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductList from "./products/ProductList";
import Home from "./home/Home";
import ProductDetails from "./products/ProductDetails";
import OrderList from "./orders/OrderList";
import OrderDetails from "./orders/OrderDetails"

class App extends Component {
    render() {
        return (
          <Router>
              <Switch>
                  <Route path="/products/:id" component={ProductDetails}/>
                  <Route path="/products" exact={true} component={ProductList}/>
                  <Route path="/orders/:id" component={OrderDetails}/>
                  <Route path="/orders" exact={true} component={OrderList}/>
                  <Route path="/home" exact={true} component={Home}/>
              </Switch>
          </Router>
        );
    }
}

export default App;
