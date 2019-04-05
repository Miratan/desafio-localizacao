import axios from 'axios';

import {
    LIST_DASHBOARD,
    LIST_DASHBOARD_SUCCESS,
    LIST_DASHBOARD_ERROR,
} from '../constants';

export const list = () => ({
    type: LIST_DASHBOARD,
});

export const listSuccess = (data) => ({
    type: LIST_DASHBOARD_SUCCESS,
    payload: { data },
});

export const listErro = (erro) => ({
    type: LIST_DASHBOARD_ERROR,
    payload: { erro },
});

export const listData = (payload) => {
    return dispatch => {
        dispatch(list());
        return axios.get(`routes/${payload}`)
                    .then(res => {
                        dispatch(listSuccess(res.data));
                        return res.data;
                    })
                    .catch(error => dispatch(listErro(error)));
    };
}