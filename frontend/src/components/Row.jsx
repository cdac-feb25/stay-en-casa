function Row({ children, mainAxisAlignment = 'center', crossAxisAlignment = 'center' }) {
    if(!children) {
        throw new Error('Children required !!!');
    }

    const style = {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: mainAxisAlignment,
        alignItems: crossAxisAlignment,
    };


    return (
        <div style={style}>
            {children}
        </div>
    );
}

export default Row;
