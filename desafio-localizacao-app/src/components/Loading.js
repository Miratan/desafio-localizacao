import React, { Component } from 'react';
import Typography from '@material-ui/core/Typography';

class Loading extends Component {
    
    render () {
        return (
            <div className='main-content'>
                <Typography variant='h4' style={{textAlign: 'center', paddingBottom: '20px'}}>
                    Carregando informações...
                </Typography>
            </div>
        );
    }
}

export default Loading;