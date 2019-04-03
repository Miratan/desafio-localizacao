import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import TopMenu from './TopMenu';
import SideMenu from './SideMenu';
import Dashboard from '../containers/Dashboard/Dashboard';
import Employee from '../containers/Employee/Employee';
import Store from '../containers/Store/Store';
import MapEmployeeStore from '../containers/MapEmployeeStore/MapEmployeeStore';

class App extends Component {

  state = {
    open: false
  }

  componentDidMount() {
    window.addEventListener('closeMenu', this.closeMenu);
  }

  componentWillUnmount() {
    window.removeEventListener('closeMenu');
  }

  handleMenu = () => {
    this.setState({ open: !this.state.open });
  }

  closeMenu = () => {
    if (this.state.open) {
      this.handleMenu();
    }
  }

  render() {
    const { open } = this.state;

    return (
      <div>
          <BrowserRouter>
            <TopMenu handleMenu={this.handleMenu}/>
            <SideMenu open={open} />
            <Switch>
              <Route path="/" exact={true} component={Dashboard} />
              <Route path="/employees" exact={true} component={Employee} />
              <Route path="/stores" exact={true} component={Store} />
              <Route path="/employees/:id/stores" exact={true} component={MapEmployeeStore} />
              <Route path="*" component={Dashboard} />
            </Switch>
          </BrowserRouter>
      </div>
    );
  }
}

export default App;
