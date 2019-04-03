import {
    LIST_EMPLOYEE,
    LIST_EMPLOYEE_SUCCESS,
    LIST_EMPLOYEE_ERROR,
} from '../constants';

const initialState = {
    data: [],
    loading: true,
    erro: false,
};

export default function homeReducer(state = initialState, action) {
    switch (action.type) {
        case LIST_EMPLOYEE:
            return {
                ...state,
                loading: true,
                erro: null,
            }
        case LIST_EMPLOYEE_SUCCESS:
            return {
                ...state,
                loading: false,
                data: action.payload.data,
            }
        case LIST_EMPLOYEE_ERROR:
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