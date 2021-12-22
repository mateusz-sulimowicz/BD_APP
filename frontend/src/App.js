import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductList from "./products/ProductList";
import Home from "./home/Home";
import ProductDetails from "./products/ProductDetails";
import OrderList from "./orders/OrderList";
import OrderDetails from "./orders/OrderDetails"
import ComposeOrder from "./orders/compose/ComposeOrder";
import AppNavBar from "./util/AppNavBar";
import ItemList from "./items/ItemList";
import ItemEdit from "./items/ItemEdit"

class App extends Component {
    render() {
        return (
          <Router>
              <AppNavBar/>
              <Switch>
                  <Route path='/items/:id' component={ItemEdit}/>
                  <Route path="/items"  exact={true} component={ItemList}/>
                  <Route path="/products/composeOrder/:id" component={ComposeOrder}/>
                  <Route path="/products/:id" component={ProductDetails}/>
                  <Route path="/products" exact={true} component={ProductList}/>
                  <Route path="/orders/:id" component={OrderDetails}/>
                  <Route path="/orders" exact={true} component={OrderList}/>
                  <Route path="/" exact={true} component={Home}/>
              </Switch>
          </Router>
        );
    }
}

export default App;
