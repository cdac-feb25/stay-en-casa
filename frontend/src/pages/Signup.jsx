import AppLogo from '../assets/stay_en_casa-nobg.png';
import Container from '../components/Container.jsx';
import MyButton from '../components/Button.jsx';
import SizedBox from '../components/SizedBox.jsx';
import { Box, TextField, Typography, Link, Divider } from '@mui/material';
import { LoginOutlined } from '@mui/icons-material';
import DividerWithText from '../components/DividerWithText.jsx';
import AppConstant from '../utils/AppConstant.js';
import SignupHelper from '../services/SignupService.js';

const signupInputFieldIds = {
    email: 'signup-email',
    password: 'signup-password',
};

function Signup() {
    return (
        <div style={{
            height: '100vh',
            width: '100vw',
            maxWidth: 400,
        }}>
            <Container style={{width: '100%'}}>
                <Box
                    component="img"
                    src={AppLogo}
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

                <TextField
                    id='signup-email'
                    variant='outlined'
                    label='Email'
                    placeholder='your@gmail.com'
                    type='email'
                    sx={{ mb: 2 }}
                >
                </TextField>

                <TextField
                    id='signup-password'
                    variant='outlined'
                    label='Password'
                    placeholder='********'
                    type='password'
                    sx={{ mb: 5 }}
                >
                </TextField>
                
                <MyButton title="Signup" onPress={SignupHelper.handleSignup} />

                <DividerWithText text={'or'} />

                <LinkText text={"Already have an account? Login"} url={'/login'} />
                
            </Container>
        </div>
    );
}

function LinkText({ text, url }) {
    return (
        <Link 
            href={url}
            sx={{
                m: 1,
                width: '100%',
                textAlign: 'center',
                fontSize: '0.9em'
            }}
        >
            {text}
        </Link>
    );
}

export default Signup;
export { signupInputFieldIds };