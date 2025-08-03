import { Box } from "@mui/material";

function Row({ children, marginTop = 0, marginBottom = 0, justifyContent = 'center', alignItems = 'center' }) {
    if(!children) {
        throw new Error('Children required !!!');
    }

    return (
        <Box 
            sx={{
                width: "100%",
                display: 'flex',
                flexDirection: 'row',
                justifyContent: justifyContent,
                alignItems: alignItems,
                marginTop: marginTop,
                marginBottom: marginBottom,
            }}
        >
            {children}
        </Box>
    );
}

export default Row;
