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
import TableFooter from '@material-ui/core/TableFooter';
import TablePagination from '@material-ui/core/TablePagination';

import { list, listData } from '../../actions/store';
import Loading from '../../components/Loading';
import Error from '../../components/Error';

const rowsPerPage = 5;

class Store extends Component {

    state = {
        page: 0,
    }

    componentDidMount() {
        const { page } = this.state;
        this.props.dispatch(listData(page));
    }

    componentWillUnmount() {
        this.props.dispatch(list());
    }

    handleChangePage = (event, page) => {
        this.props.dispatch(listData(page));
        this.setState({ page });
    }

    render () {
        const { loading, erro, data, classes } = this.props;
        const { page } = this.state;

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
                            {data.stores.map(item => (
                                <TableRow key={item.id}>
                                    <TableCell>{item.name}</TableCell>
                                    <TableCell>{item.latitude}</TableCell>
                                    <TableCell>{item.longitude}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                        <TableFooter>
                            <TableRow>
                                <TablePagination
                                    count={data.total}
                                    rowsPerPage={rowsPerPage}
                                    rowsPerPageOptions={[0]}
                                    page={page}
                                    onChangePage={this.handleChangePage}/>
                            </TableRow>
                        </TableFooter>
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