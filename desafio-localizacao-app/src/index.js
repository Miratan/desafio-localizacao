import React from 'react';
import ReactDOM from 'react-dom';
import { ClickAwayListener } from '@material-ui/core';

import './index.css';
import App from './app/App';
import * as serviceWorker from './serviceWorker';

const closeMenu = () => {
    dispatchEvent(new Event('closeMenu'));
}

ReactDOM.render(
    <ClickAwayListener onClickAway={closeMenu}>
        <App />
    </ClickAwayListener>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
