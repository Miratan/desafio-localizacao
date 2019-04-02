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

export const listData = () => {
    return dispatch => {
        return axios.get(`routes/bigger-proximity-grouped-by-employee`)
                    .then(res => {
                        dispatch(listSuccess(res.data));
                        return res.data;
                    })
                    .catch(error => dispatch(listErro(error)));
    };
}