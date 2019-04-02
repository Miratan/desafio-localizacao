import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import MenuIcon from '@material-ui/icons/Menu';

class TopMenu extends Component {

    render() {
        const { classes, handleMenu } = this.props;

        return (
            <div>
                <AppBar position="static">
                    <Toolbar>
                        <IconButton
                            onClick={handleMenu}
                            className={classes.menuButton}
                            color="inherit"
                            aria-label="Abrir menu">
                            <MenuIcon />
                        </IconButton>
                        <Typography
                            className={classes.title}
                            variant="h6"
                            color="inherit"
                            noWrap>
                            Desafio Localização
                        </Typography>
                    </Toolbar>
                </AppBar>
            </div>
        );
    }
}

TopMenu.propTypes = {
    classes: PropTypes.object.isRequired,
    handleMenu: PropTypes.func.isRequired,
};

const styles = () => ({
    menuButton: {
      marginLeft: -12,
      marginRight: 20,
    },
    title: {
    },
});

TopMenu = withStyles(styles)(TopMenu);
export default TopMenu;