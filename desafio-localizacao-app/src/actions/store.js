import axios from 'axios';

import {
    LIST_STORE,
    LIST_STORE_SUCCESS,
    LIST_STORE_ERROR,
} from '../constants';

export const list = () => ({
    type: LIST_STORE,
});

export const listSuccess = (data) => ({
    type: LIST_STORE_SUCCESS,
    payload: { data },
});

export const listErro = (erro) => ({
    type: LIST_STORE_ERROR,
    payload: { erro },
});

export const listData = () => {
    return dispatch => {
        return axios.get(`stores`)
                    .then(res => {
                        dispatch(listSuccess(res.data));
                        return res.data;
                    })
                    .catch(error => dispatch(listErro(error)));
    };
}