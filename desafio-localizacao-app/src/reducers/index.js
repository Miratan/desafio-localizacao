import { combineReducers } from 'redux';

import dashboard from './dashboard';
import employee from './employee';
import store from './store';

const rootReducer = combineReducers({
    dashboard: dashboard,
    employee: employee,
    store: store,
})
  
export default rootReducer