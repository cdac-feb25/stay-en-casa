import { AccountCircle, Book, Logout, Note } from "@mui/icons-material";
import { Box, Menu, MenuItem, MenuList, Typography } from "@mui/material";
import Row from "./Row";
import SizedBox from "./SizedBox";
import React from "react";
import Colors from "../utils/Colors";
import Navigate from "../services/NavigationService";
import AppRoutes from "../utils/AppRoutes";
import ApiCaller from "../services/ApiCaller";

function HomeAccountMenu({ anchorEl, setAnchorEl }) {
    const toOpen = Boolean(anchorEl);

    const onEditProfile = () => {
        onClose();
        Navigate.to({ path: AppRoutes.editProfile });
    }
    
    const onMyBooking = () => {
        onClose();
    }

    const onLogout = () => {
        onClose();
        ApiCaller.logout();
    }

    const onClose = () => {
        setAnchorEl(null);
    }

    return (
        <Menu
            anchorEl={anchorEl}
            open={ toOpen }
            onClose={ onClose }
        >
            <MenuItem onClick={ onEditProfile } >
                <React.Fragment>
                    <AccountCircle />
                    <SizedBox width={10} />
                    <Typography>Profile</Typography>
                </React.Fragment>
            </MenuItem>

            <MenuItem onClick={ onMyBooking } >
                <React.Fragment>
                    <Note />
                    <SizedBox width={10} />
                    <Typography>My Booking</Typography>
                </React.Fragment>
            </MenuItem>

            <MenuItem onClick={ onLogout } >
                <React.Fragment>
                    <Logout />
                    <SizedBox width={10} />
                    <Typography>Logout</Typography>
                </React.Fragment>
            </MenuItem>
        </Menu>
    );
}

export default HomeAccountMenu;