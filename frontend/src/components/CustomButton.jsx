import { Button, colors } from "@mui/material";
import Colors from "../utils/Colors";
import { LoginRounded } from "@mui/icons-material";

function CustomButton({ 
    title = "Button", 
    onPress, 
    disabled=false,
    bgColor=Colors.white,
    textColor=Colors.background,
    hover={},
    type = 'button', // button, submit, reset
    startIcon = null,
    endIcon = null,
    fullWidth=true,
}) {
    return(
        <Button 
            variant="contained"
            onClick={onPress}
            disabled={disabled}
            type={type}
            startIcon={startIcon}
            endIcon={endIcon}
            fullWidth={fullWidth}
            sx={{ 
                mb: 2,
                backgroundColor: bgColor,
                color: textColor,
                ":hover": hover
            }}
        >
            {title}
        </Button>
    );
}

export default CustomButton;