import { createSlice } from '@reduxjs/toolkit';

export const currentPage = createSlice({
    name: 'currentPage',
    initialState: {
        value: 0
    },
    reducers: {
        setPage: (state, action) => {
            // console.log(action.payload);
            state.value = action.payload; // Use action.payload to set the new value
        }
    }
});

// Action creators are generated for each case reducer function
export const { setPage } = currentPage.actions;

export default currentPage.reducer;