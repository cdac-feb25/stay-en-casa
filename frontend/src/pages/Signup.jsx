import Container from '../components/Container.jsx';
import CustomButton from '../components/CustomButton.jsx';
import { Box, TextField, Typography, Link, Divider, Backdrop, CircularProgress } from '@mui/material';
import DividerWithText from '../components/DividerWithText.jsx';
import AppConstant from '../utils/AppConstant.js';
import AppRoutes from '../utils/AppRoutes.js';
import IdHelper from '../utils/IdHelper.js';
import Colors from '../utils/Colors.js';
import { useNavigate } from 'react-router-dom';
import handleSignupFormSubmit from '../services/SignupService.js';
import React from 'react';
import AssetHelper from '../utils/AssetHelper.js';

function Signup() {
    const [ isLoading, setIsLoading ] = React.useState(false);
    const [ errorMsg, setErrorMsg ] = React.useState("");
    const [ showError, setShowError ] = React.useState(false);
    const [ showPasswordComparison, setShowPasswordComparison ] = React.useState(false);
    const [ isPasswordMatched, setPasswordMatched ] = React.useState(false);

    const passwordMsg = {
        matched: "Yup! Password matched.",
        unMatched: "Oops! Password do not match.",
    }

    /**
     * @param {event} event 
     */
    const handleConfirmPassword = (event) => {
        if(!showPasswordComparison) {
            setShowPasswordComparison(true);
        }
        const password = document.getElementById(IdHelper.signupPassword).value;
        
        if(password === event.target.value) {
            setPasswordMatched(true);
        } else {
            setPasswordMatched(false);
        }
    }

    return (
        <div style={{
            height: '100vh',
            width: '100vw',
            maxWidth: 400,
        }}>
            <Container style={{width: '100%'}}>
                <Box
                    component="img"
                    src={AssetHelper.appIconWithName}
                    alt="Box Image"
                    sx={{ 
                        width: 200, 
                        height: 'auto', 
                        borderRadius: 2,
                     }}
                />
                
                <Typography sx={{mb: 5, fontSize: '1.35em'}}>
                    {AppConstant.signupTagline}
                </Typography>

                <Typography 
                    id={IdHelper.signupErrorDiv}
                    style={{marginBottom: "15px", fontSize: '0.8em', color: Colors.error}}
                    hidden={showError === false}
                >
                    {errorMsg}
                </Typography>

                <form 
                    id={IdHelper.signupForm}
                    onSubmit={ handleSignupFormSubmit(setIsLoading, setErrorMsg, setShowError) }
                >
                    <TextField
                        id={IdHelper.signupEmail}
                        variant='outlined'
                        label='Email'
                        name='email'
                        placeholder='your@gmail.com'
                        type='email'
                        sx={{ mb: 2 }}
                        autoComplete='username'
                        required={true}
                    />

                    <TextField
                        id={IdHelper.signupPassword}
                        variant='outlined'
                        label='Password'
                        name='password'
                        placeholder='********'
                        type='password'
                        autoComplete='new-password'
                        required={true}
                        sx={{ mb: 2 }}
                    />
                    
                    <TextField
                        // id={IdHelper.signupConfirmPassword}
                        variant='outlined'
                        label='Confirm Password'
                        placeholder='********'
                        type='password'
                        name='confirmPassword'
                        autoComplete='new-password'
                        required={true}
                        onChange={handleConfirmPassword}
                        sx={{ mb: 1 }}
                    />

                    <Typography
                        style={{ 
                            fontSize: "0.8em",
                            marginBottom: "1.4em", 
                            textAlign: "start",
                            color: `${isPasswordMatched ? Colors.success : Colors.error}`,
                        }}
                        visibility={ showPasswordComparison ? 'visible' : 'hidden'}
                    >
                        {isPasswordMatched ? passwordMsg.matched : passwordMsg.unMatched}
                    </Typography>
                    
                    <CustomButton title="Signup" type='submit' mb={2} />
                </form>

                <DividerWithText text={'or'} />

                <Typography sx={{ color: Colors.white }}>
                    Already have an account ? <Link href={AppRoutes.login} style={{ fontSize: "1em" }}> Login</Link>
                </Typography>
            </Container>

            <Backdrop open={isLoading} >
                <CircularProgress />
            </Backdrop>
        </div>
    );
}

export default Signup;