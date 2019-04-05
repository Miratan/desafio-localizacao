import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';

class GroupTypeFilter extends Component {

    handleChange = event => {
        this.props.handleChange(event.target.value);
    };

    render () {
        const { classes, groups, group } = this.props;

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
                        value={group}
                        onChange={this.handleChange}
                        margin="normal"
                        variant="outlined" >
                        {groups.map(option => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>
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
});

GroupTypeFilter = withStyles(styles)(GroupTypeFilter);
export default GroupTypeFilter;