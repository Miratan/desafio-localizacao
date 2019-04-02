import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { withStyles } from '@material-ui/core/styles';
import MenuList from '@material-ui/core/MenuList';
import MenuItem from '@material-ui/core/MenuItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import HomeIcon from '@material-ui/icons/Home';
import SwipeableDrawer from '@material-ui/core/SwipeableDrawer';

class SideMenu extends Component {

    render() {
        const { open, classes } = this.props;
        const menuOptions = [
            { url: 'home', label: 'Dashboard' }
        ];

        return (
            <div>
                <SwipeableDrawer open={open} onOpen={() => {}} onClose={() => {}}>
                    <MenuList className={classes.menuSize}>
                        {menuOptions.map(option => (
                            <Link key={option.url} to={option.url} className={classes.link}>
                                <MenuItem>
                                    <ListItemIcon>
                                        <HomeIcon />
                                    </ListItemIcon>
                                    <ListItemText inset primary={option.label} />
                                </MenuItem>
                            </Link>
                        ))}
                    </MenuList>
                </SwipeableDrawer>
            </div>
        );
    }
}

SideMenu.propTypes = {
    open : PropTypes.bool.isRequired
}

const styles = () => ({
    menuSize: {
        width: 250,
    },
    link: {
        textDecoration: 'none',
    },
});

SideMenu = withStyles(styles)(SideMenu);
export default SideMenu;