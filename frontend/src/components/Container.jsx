import Colors from "../utils/Colors";
import Column from "./Column.jsx";

function Container({ 
    children, 
    padding: { top = '30px', right = '30px', bottom = '30px', left = '30px' } = {},
}) {
    const style = {
        padding: `${top} ${right} ${bottom} ${left}`,
        border: `1px solid ${Colors.grey}`,
        borderRadius: '10px',
    };

    return (
        <div 
            style={style}
        >
            <Column>
                {children}
            </Column>
        </div>
    );
}

export default Container;