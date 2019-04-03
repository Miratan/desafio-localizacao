import axios from 'axios';

import {
    LIST_EMPLOYEE,
    LIST_EMPLOYEE_SUCCESS,
    LIST_EMPLOYEE_ERROR,
} from '../constants';

export const list = () => ({
    type: LIST_EMPLOYEE,
});

export const listSuccess = (data) => ({
    type: LIST_EMPLOYEE_SUCCESS,
    payload: { data },
});

export const listErro = (erro) => ({
    type: LIST_EMPLOYEE_ERROR,
    payload: { erro },
});

export const listData = () => {
    return dispatch => {
        return axios.get(`employees`)
                    .then(res => {
                        dispatch(listSuccess(res.data));
                        return res.data;
                    })
                    .catch(error => dispatch(listErro(error)));
    };
}