import React, { Component } from 'react';
import classnames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Typography from '@material-ui/core/Typography';
import Collapse from '@material-ui/core/Collapse';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import IconButton from '@material-ui/core/IconButton';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';

class EmployeeStores extends Component {

    state = { expanded: false };

    handleExpandClick = () => {
        this.setState(state => ({ expanded: !state.expanded }));
    };

    render () {
        const { classes, employee, stores } = this.props;

        return (
            <Grid item xs={12} sm={6} md={4}>
                <Card className={classes.paper}>
                    <CardContent style={{ height: '45%' }}>
                        <Grid container justify="center" alignItems="center" style={{ padding: '10px' }}>
                            <Avatar>{ employee.name.substring(0, 1) }</Avatar>
                        </Grid>
                        <Typography variant="h5" gutterBottom={true}>
                            { employee.name }
                        </Typography>
                        <Typography variant="caption" gutterBottom={true}>
                            Lojas Próximas: { stores.length }
                        </Typography>
                    </CardContent>
                    <CardActions className={classes.actions} disableActionSpacing>
                        <Link to={`/employees/${ employee.id }/stores`}
                            className={classnames('link-button', classes.linkButton)}>
                            <Button size="small" color="primary" variant="outlined">
                                Ver mapa
                            </Button>
                        </Link>
                        <IconButton
                            style={{float: 'right'}}
                            className={classnames(classes.expand, {
                            [classes.expandOpen]: this.state.expanded,
                            })}
                            onClick={this.handleExpandClick}
                            aria-expanded={this.state.expanded}
                            aria-label="Ver mais">
                            <ExpandMoreIcon />
                        </IconButton>
                    </CardActions>
                    <Collapse in={this.state.expanded} timeout="auto" unmountOnExit>
                        <CardContent style={{ margin: '0px 0px 0px 15px', textAlign: 'left' }}>
                            {stores.map(store => (
                                <Typography key={store.id} style={{ display: 'list-item' }}>
                                    {store.name}
                                </Typography>
                            ))}
                        </CardContent>
                    </Collapse>
                </Card>
            </Grid>
        );
    }
}

const styles = () => ({
    paper: {
        textAlign: 'center',
    },
    actions: {
        display: 'flex',
    },
    expand: {
        transform: 'rotate(0deg)',
        marginLeft: 'auto',
    },
    expandOpen: {
        transform: 'rotate(180deg)',
    },
    linkButton: {
        paddingLeft: '10px',
    }
}); 

EmployeeStores = withStyles(styles)(EmployeeStores);
export default EmployeeStores