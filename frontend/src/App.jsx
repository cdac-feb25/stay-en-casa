import * as React from 'react';
import Colors from './utils/Colors';
import Login from './pages/Login';
import './assets/fonts/nunito-bold.css';
import Signup from './pages/Signup';
import { Route, Router, Routes } from 'react-router-dom';

function App() {
  React.useEffect(() => {
    document.body.style.background = Colors.background;
    document.body.style.color = Colors.primary;
    document.body.style.fontFamily = 'Nunito, sans-serif';
  }, []); // Run only once on mount

  return (
    <Routes>
      {/* <Route path="/" element={<Home />} /> */}
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      {/* <Route path="/account" element={<Account />} /> */}
    </Routes>
  )
}

export default App;
