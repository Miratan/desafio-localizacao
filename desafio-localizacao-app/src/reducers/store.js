import {
    LIST_STORE,
    LIST_STORE_SUCCESS,
    LIST_STORE_ERROR,
} from '../constants';

const initialState = {
    data: [],
    loading: true,
    erro: false,
};

export default function homeReducer(state = initialState, action) {
    switch (action.type) {
        case LIST_STORE:
            return {
                ...state,
                loading: true,
                erro: null,
            }
        case LIST_STORE_SUCCESS:
            return {
                ...state,
                loading: false,
                data: action.payload.data,
            }
        case LIST_STORE_ERROR:
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