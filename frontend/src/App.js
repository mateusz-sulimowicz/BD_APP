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
import ProductEdit from "./products/ProductEdit";
import ModuleEdit from "./products/module/ModuleEdit";
import OptionEdit from "./products/module/OptionEdit";

class App extends Component {
    render() {
        return (
          <Router>
              <AppNavBar/>
              <Switch>
                  <Route path='/options/:productId/:moduleId/:id' component={OptionEdit}/>
                  <Route path='/modules/:productId/:id' component={ModuleEdit}/>
                  <Route path='/items/:id' component={ItemEdit}/>
                  <Route path="/items"  exact={true} component={ItemList}/>
                  <Route path="/products/composeOrder/:id" component={ComposeOrder}/>
                  <Route path="/products/details/:id/:action" component={ProductDetails}/>
                  <Route path="/products/:id" component={ProductEdit}/>
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
