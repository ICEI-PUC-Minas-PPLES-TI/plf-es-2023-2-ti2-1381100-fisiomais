import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import thunk from 'redux-thunk'; // Import the thunk middleware
import currentPageReducer from './currentPage';
import mediaDetailReducer from './mediaDetail';
import currentUserReducer from './currentUser';

const middleware = [...getDefaultMiddleware(), thunk]; // Include thunk in the middleware array

const store = configureStore({
    reducer: {
        currentPage: currentPageReducer,
        mediaDetail: mediaDetailReducer,
        currentUser: currentUserReducer,
    },
    middleware,
});

export default store;