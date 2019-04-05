import React, { Component } from 'react';
import { connect } from 'react-redux';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';

import { list, listData } from '../../actions/dashboard';
import EmployeeStores from './components/EmployeeStores';
import Loading from '../../components/Loading';
import Error from '../../components/Error';
import GroupTypeFilter from './components/GroupTypeFilter';
import { groups } from './DashboardConstants';

class Dashboard extends Component {

    state = {
        group: groups[0],
    }

    componentDidMount() {
        this.props.dispatch(listData(groups[0].value));
    }

    componentWillUnmount() {
        this.props.dispatch(list());
    }

    filter = (payload) => {
        this.props.dispatch(listData(payload));
    }

    handleChange = (payload) => {
        const { group } = this.state;
        if (group.value === groups.find(group => group.value === payload).value) {
            return;
        }

        this.setState({ group: groups.find(group => group.value === payload) });
        this.filter(payload);
    }

    render () {
        const { loading, erro, data } = this.props;
        const { group } = this.state;

        if (loading) { return <Loading /> }

        if (erro) { return <Error /> }

        return (
            <div className='main-content'>
                <Typography variant='h4' className='main-title'>
                    Dashboard
                </Typography>

                <GroupTypeFilter filter={this.filter}
                    handleChange={this.handleChange}
                    group={group.value}
                    groups={groups} />

                <Grid container spacing={24} style={{paddingTop: '20px'}}>
                    {data && data.map(element => (
                        <EmployeeStores key={element.employee.id}
                            {...element}
                            group={group}  />
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