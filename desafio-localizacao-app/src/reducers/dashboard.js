import {
    LIST_DASHBOARD,
    LIST_DASHBOARD_SUCCESS,
    LIST_DASHBOARD_ERROR,
} from '../constants';

const initialState = {
    data: [],
    loading: true,
    erro: false,
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