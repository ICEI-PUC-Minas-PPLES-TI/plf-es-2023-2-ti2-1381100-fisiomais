import { configureStore } from '@reduxjs/toolkit'
import currentPageReducer from './currentPage'
import mediaDetailReducer from './mediaDetail'
import currentUserReducer from './currentUser'

export default configureStore({
    reducer: {
        currentPage: currentPageReducer,
        mediaDetail: mediaDetailReducer,
        currentUser: currentUserReducer
    }
})