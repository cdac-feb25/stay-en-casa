// {/* <Route path="/" element={<Home />} /> */}
// <Route path="/login" element={<Login />} />
// <Route path="/signup" element={<Signup />} />
// {/* <Route path="/account" element={<Account />} /> */}

// {/* <Route path="/" element={<BookingPage />} /> */}
// <Route path="/booking-page" element={<BookingPage />} />
// <Route path="/my-bookings" element={<MyBookingsPage />} />
// <Route path="/booking/:bookingId" element={<BookingDetailsPage />} />
// <Route path='/update-booking/:bookingId' element={<UpdateBookingStatus />} />

const AppRoutes = {
    "/": "/",
    home: "/home",
    login: "/login",
    signup: "/signup",
    forgotPassword: "/forgot-password",
    account: "/account",
    editProfile: "/edit-profile",
    bookingPage: "/booking-page",
    myBookings: "/my-bookings",
    bookingById: "/booking/:bookingId",
    updateBookingById: "/update-booking/:bookingId",
};

export default AppRoutes;