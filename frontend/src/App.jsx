import * as React from 'react';
import Colors from './utils/Colors';
import Login from './pages/Login';
import './assets/fonts/nunito-bold.css';
import Signup from './pages/Signup';
import { Route, Routes, useNavigate } from 'react-router-dom';
import BookingPage from './pages/BookingPage';
import MyBookingsPage from './pages/MyBookingsPage';
import Navbar from './components/Navbar';
import BookingDetailsPage from './pages/BookingDetailsPage';
import UpdateBookingStatus from './pages/UpdateBookingStatus';
import { Box } from '@mui/material';
import AppRoutes from './utils/AppRoutes';
import Home from './pages/Home';
import MyAccount from './pages/MyAccount';
import EditProfile from './pages/EditProfile';
import UserContext from './utils/UserContext';
import LocalStorageHelper from './utils/LocalStorageHelper';
import Navigate from './services/NavigationService';
import ErrorDialog from './components/ErrorDialog';
import { GlobalVariable, useGlobalState } from './pages/GlobalProvider';
import AllPropertiesPage from './pages/AllPropertiesPage';

function App() {
  const navigate = useNavigate();

  React.useEffect(() => {
    document.body.style.background = Colors.background;
    document.body.style.color = Colors.primary;
    document.body.style.fontFamily = 'Nunito, sans-serif';

    UserContext.setLoggedInUser(LocalStorageHelper.getUserProfile());

    Navigate.set(navigate);
  }, [navigate]); // Run only once on mount

  const bodyStyle = {
    marginTop: "50px",
  }

  // const globalVariable = GlobalVariable;
  // const { [globalVariable.errorDialogProp]: errorDialogProp, [globalVariable.setErrorDialogProp]: setErrorDialogProp } = useGlobalState();

  return (
    <>
      <Navbar />
        
      <Box sx={bodyStyle}>
        <Routes>
          <Route path={AppRoutes['/']} element={<Home />} />  {/* / -> Home */}
          <Route path={AppRoutes.home} element={<Home />} />  {/* /home -> Home */}
          <Route path={AppRoutes.login} element={<Login />} />  {/* /login -> Login */} 
          <Route path={AppRoutes.signup} element={<Signup />} />  {/* /signup -> Signup */} 
          <Route path={AppRoutes.account} element={<MyAccount />} />  {/* /account -> MyAccount */} 
          <Route path={AppRoutes.editProfile} element={ <EditProfile /> } />
          
          {/* <Route path="/" element={<BookingPage />} /> */}
          <Route path={AppRoutes.bookingPage} element={<BookingPage />} />
          <Route path={AppRoutes.myBookings} element={<MyBookingsPage />} />
          <Route path={AppRoutes.bookingById} element={<BookingDetailsPage />} />
          <Route path={AppRoutes.updateBookingById} element={<UpdateBookingStatus />} />
          <Route path={AppRoutes.properties} element={ <AllPropertiesPage /> } />
        </Routes>
      </Box>

      {/* <ErrorDialog 
        showDialog={errorDialogProp.showDialog}
        msg={errorDialogProp.dialogMsg}

      /> */}
    </>
  );
}

export default App;
