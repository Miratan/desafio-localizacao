import React, { Component } from 'react';
import { connect } from 'react-redux';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

import { list, listData } from '../../actions/store';
import Loading from '../../components/Loading';
import Error from '../../components/Error';

class Store extends Component {

    componentDidMount() {
        this.props.dispatch(listData());
    }

    componentWillUnmount() {
        this.props.dispatch(list());
    }

    render () {
        const { loading, erro, data, classes } = this.props;

        if (loading) { return <Loading /> }

        if (erro) { return <Error /> }

        return (
            <div className='main-content'>
                <Typography variant='h4' className='main-title'>
                    Lojas
                </Typography>

                <Paper className={classes.root}>
                    <Table className={classes.table}>
                        <TableHead>
                            <TableRow>
                                <TableCell>Nome</TableCell>
                                <TableCell>Latitude</TableCell>
                                <TableCell>Longitude</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {data.map(item => (
                                <TableRow key={item.id}>
                                    <TableCell>{item.name}</TableCell>
                                    <TableCell>{item.latitude}</TableCell>
                                    <TableCell>{item.longitude}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </Paper>
            </div>
        );
    }
}


const styles = () => ({
    root: {
      width: '100%',
      overflowX: 'auto',
    },
    table: {
      minWidth: 500,
    },
});

const mapStateToProps = state => {
    return {
        data: state.store.data,
        loading: state.store.loading,
        erro: state.store.erro,
    }
}

Store = withStyles(styles)(Store)
Store = connect(mapStateToProps)(Store)
export default Store;