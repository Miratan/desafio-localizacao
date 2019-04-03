import {
    LIST_EMPLOYEE,
    LIST_EMPLOYEE_SUCCESS,
    LIST_EMPLOYEE_ERROR,
    EMPLOYEE_MAP,
    EMPLOYEE_MAP_SUCCESS,
    EMPLOYEE_MAP_ERROR,
} from '../constants';

const initialState = {
    data: [],
    dataMap: [],
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
        case EMPLOYEE_MAP:
            return {
                ...state,
                loading: true,
                erro: null,
            }
        case EMPLOYEE_MAP_SUCCESS:
            return {
                ...state,
                loading: false,
                dataMap: action.payload.data,
            }
        case EMPLOYEE_MAP_ERROR:
            return {
                ...state,
                loading: false,
                erro: true,
                dataMap: [],
            }
        default:
            return state;
    }
}