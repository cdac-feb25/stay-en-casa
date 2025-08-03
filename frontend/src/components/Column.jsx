import { Box } from "@mui/material";

function Column({ children, marginLeft = 0, marginRight = 0, justifyContent = 'center', alignItems = 'center' }) {
    if(!children) {
        throw new Error('Children required !!!');
    }

    return (
        <Box 
            sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: justifyContent,
                alignItems: alignItems,
                marginLeft: marginLeft,
                marginRight: marginRight,
            }}
        >
            {children}
        </Box>
    );
}

export default Column;