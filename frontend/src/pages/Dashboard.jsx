// import { Button, Drawer } from "@mui/material";
import ApiCaller from "../services/ApiCaller";
import RedirectionHelper from "../services/RedirectionHelper";
import Column from "../components/Column";

import React, { useEffect, useState } from 'react';
import { Container, Box, Typography, Button, CircularProgress } from '@mui/material';
import getLocation from "../services/LocationService";


function Dashboard() {
    RedirectionHelper.fromHome();
    
    return (
        <div>
            <h1>Dashboard Page (After Login — Buyer, Seller, or Admin)</h1>
            <h3>CallToAction btn [Add new property, Edit/Delete existing properties, Saved properties / favorites]</h3>

            <Column>

                <Button sx={{ mb:2 }} onClick={ () => { ApiCaller.refreshToken(); } }>
                    Refresh token
                </Button>

            </Column>
        </div>
    );
}

export default Dashboard;