import { Button, Drawer } from "@mui/material";
import ApiCaller from "../services/ApiCaller";
import { ExitToApp } from "@mui/icons-material";
import ApiActionHelper from "../utils/ApiActionHelper";
import RedirectionHelper from "../services/RedirectionHelper";
import Column from "../components/Column";
import HomeAccountMenu from "../components/HomeAccountMenu";

function Home() {
    RedirectionHelper.fromHome();

    function testRefreshToken() {
        
    }

    return (
        <div>
            <h1>Home Page</h1>
            <Column>
                <Button sx={{ mb:2 }} onClick={ () => { ApiCaller.refreshToken(); } }>
                    Refresh token
                </Button>


            </Column>
        </div>
    );
}

export default Home;