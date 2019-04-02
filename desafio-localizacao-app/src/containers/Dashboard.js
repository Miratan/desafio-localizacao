import React, { Component } from 'react';
import Typography from '@material-ui/core/Typography';

class Dashboard extends Component {

    render () {
        return (
            <div className='main-content'>
                <Typography variant='h4' className='main-title'>
                    Dashboard
                </Typography>
            </div>
        )
    }
}

export default Dashboard;