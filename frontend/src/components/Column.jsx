function Column({ children, mainAxisAlignment = 'center', crossAxisAlignment = 'center' }) {
    if(!children) {
        throw new Error('Children required !!!');
    }

    const style = {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: mainAxisAlignment,
        alignItems: crossAxisAlignment,
    };

    return (
        <div style={style}>
            {children}
        </div>
    );
}

export default Column;