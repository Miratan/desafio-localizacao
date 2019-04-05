import {
    LIST_DASHBOARD,
    LIST_DASHBOARD_SUCCESS,
    LIST_DASHBOARD_ERROR,
} from '../constants';

import { groups } from '../containers/Dashboard/DashboardConstants';

const initialState = {
    data: [],
    loading: true,
    erro: false,
    group: groups[0],
};

export default function homeReducer(state = initialState, action) {
    switch (action.type) {
        case LIST_DASHBOARD:
            return {
                ...state,
                loading: true,
                erro: null,
            }
        case LIST_DASHBOARD_SUCCESS:
            return {
                ...state,
                loading: false,
                data: action.payload.data,
            }
        case LIST_DASHBOARD_ERROR:
            return {
                ...state,
                loading: false,
                erro: true,
                data: [],                
            }
        default:
            return state;
    }
}