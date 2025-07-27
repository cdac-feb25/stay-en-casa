function SizedBox({ width, height, children }) {
    const style = {
        display: children ? 'inline-block' : 'block',
        width: width ?? 'auto',
        height: height ?? 'auto',
    };
    
    return (
        <div style={style}>
            {children}
        </div>
    );
}

export default SizedBox;