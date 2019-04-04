import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';

const groups = [
    { value: 'PROXIMITY', label: 'Proximidade', },
    { value: 'DIVIDED', label: 'Melhor Divisão', },
];

class GroupTypeFilter extends Component {

    constructor(props) {
        super(props);

        const { group } = props;
        this.state.group = group;
    }

    state = {}

    handleChange = name => event => {
        this.setState({
            [name]: event.target.value,
        });
    };

    handleFilter = () => {
        this.props.filter(this.state.group);
    }

    render () {
        const { classes } = this.props;

        return (
            <div>
                <Grid
                    container
                    direction="row"
                    justify="center"
                    alignItems="center">
                    <TextField
                        id="select-group"
                        select
                        label="Distribuir lojas por"
                        className={classes.textField}
                        value={this.state.group}
                        onChange={this.handleChange('group')}
                        margin="normal"
                        variant="outlined" >
                        {groups.map(option => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>
                    <Button variant="contained"
                        color="primary"
                        className={classes.button}
                        onClick={this.handleFilter}>
                        Distribuir
                    </Button>
                </Grid>
            </div>
        );
    }
}

const styles = () => ({
    textField: {
        width: 200,
        maxWidth: 200,
        textAlign: 'left',
    },
    button: {
        marginLeft: '10px',
    },
});

GroupTypeFilter = withStyles(styles)(GroupTypeFilter);
export default GroupTypeFilter;