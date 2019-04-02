import React, { Component } from 'react';
import Typography from '@material-ui/core/Typography';

class Error extends Component {
    
    render () {
        return (
            <div className='main-content'>
                <Typography variant='subtitle1' style={{textAlign: 'center', paddingBottom: '20px'}}>
                    Não foi possível processar a requisição!
                </Typography>
                <Typography variant='caption' style={{textAlign: 'center', paddingBottom: '20px'}}>
                    Para maiores informações entre em contato com o administrador do sistema.
                </Typography>
            </div>
        );
    }
}

export default Error;