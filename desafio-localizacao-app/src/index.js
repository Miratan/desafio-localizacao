import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import reduxThunk from 'redux-thunk';
import { ClickAwayListener } from '@material-ui/core';

import './index.css';
import App from './app/App';
import reducers from './reducers';
import * as serviceWorker from './serviceWorker';

const createStoreWithMiddleware = applyMiddleware(reduxThunk)(createStore);
const store = createStoreWithMiddleware(reducers);
const app = document.getElementById('root');

const closeMenu = () => {
    dispatchEvent(new Event('closeMenu'));
}

axios.defaults.baseURL = 'http://localhost:9000/';

ReactDOM.render(
    <ClickAwayListener onClickAway={closeMenu}>
        <Provider store={store}>
            <App />
        </Provider>
    </ClickAwayListener>,
    app
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
