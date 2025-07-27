import { Button, colors } from "@mui/material";
import Colors from "../utils/Colors";
import { LoginRounded } from "@mui/icons-material";

function MyButton({ 
    onPress, 
    title = 'Button', 
    startIcon = null,
}) {
    return(
        <Button 
            variant="contained"
            startIcon={startIcon}
            onClick={onPress}
            fullWidth={true}
            sx={{mb: 2}}
        >
            {title}
        </Button>
    );
}

export default MyButton;