import React, { Component } from 'react';
import { connect } from 'react-redux';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';

import { list, listData } from '../../actions/dashboard';
import EmployeeStores from './components/EmployeeStores';
import Loading from '../../components/Loading';
import Error from '../../components/Error';

class Dashboard extends Component {

    componentDidMount() {
        this.props.dispatch(listData());
    }

    componentWillUnmount() {
        this.props.dispatch(list());
    }

    render () {
        const { loading, erro, data } = this.props;

        if (loading) { return <Loading /> }

        if (erro) { return <Error /> }

        return (
            <div className='main-content'>
                <Typography variant='h4' className='main-title'>
                    Dashboard
                </Typography>

                <Grid container spacing={24}>
                    {data && data.map(element => (
                        <EmployeeStores key={element.employee.id} {...element} />
                    ))}
                </Grid>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        data: state.dashboard.data,
        loading: state.dashboard.loading,
        erro: state.dashboard.erro,
    }
}

Dashboard = connect(mapStateToProps)(Dashboard)
export default Dashboard;