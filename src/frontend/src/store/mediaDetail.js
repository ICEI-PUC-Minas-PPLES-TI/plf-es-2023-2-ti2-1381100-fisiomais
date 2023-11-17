import { createSlice } from '@reduxjs/toolkit';

export const mediaDetail = createSlice({
    name: 'mediaDetail',
    initialState: {
        value: undefined
    },
    reducers: {
        setCurrentMedia: (state, action) => {
            // console.log(`setCurrentMedia: ${action.payload}`);
            localStorage.setItem('currentMedia', JSON.stringify(action.payload));
            state.value = action.payload; // Use action.payload to set the new value
        }
    }
});

// Action creators are generated for each case reducer function
export const { setCurrentMedia } = mediaDetail.actions;

export default mediaDetail.reducer;