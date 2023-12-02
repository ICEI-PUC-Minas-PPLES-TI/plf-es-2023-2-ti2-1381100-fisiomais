import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { jwtDecode } from "jwt-decode";
import axios from 'axios';

const initialState = {
    value: {
        user: null,
        token: null,
        status: 'idle',
        error: null,
    }
};

export const loginUser = createAsyncThunk('currentUser/loginUser', async (credentials, { dispatch }) => {
    try {
        const response = await fetchLoginUser(credentials);
        dispatch(login(response)); // Dispatch the success action
    } catch (error) {
        // Dispatch the failure action if an error occurs
        dispatch(loginUser.rejected(error.message));
    }
});

export const checkSession = createAsyncThunk(
    'currentUser/checkSession',
    async (_, { dispatch, getState }) => {
        const storedToken = localStorage.getItem('token');

        if (storedToken) {
            try {
                const response = await fetchLoginUser({ token: storedToken });
                dispatch(login(response)); // Dispatch the success action
            } catch (error) {
                // Dispatch the failure action if an error occurs
                dispatch(logout()); // Clear the user data if login fails
                dispatch(checkSession.rejected(error.message));
            }
        } else {
            dispatch(logout()); // Clear the user data if no token is found
        }
    }
);

export const currentUser = createSlice({
    name: 'currentUser',
    initialState,
    reducers: {
        login: (state, action) => {
            state.value.user = action.payload.user;
            state.value.token = action.payload.token;
            state.value.status = 'succeeded';
            state.value.error = null;
            localStorage.setItem('token', JSON.stringify(state.value.token));
        },
        setCurrentUser: (state, action) => {
            state.value.user = action.payload;
            const tokenStored = localStorage.getItem('token');
            if (tokenStored) {
                state.value.token = JSON.parse(tokenStored);
            }
        },
        logout: (state) => {
            state.value.user = null;
            state.value.status = 'idle';
            state.value.error = null;
            localStorage.removeItem('token');
        },
        setLoginStatus: (state, action) => {
            state.value.status = action.payload;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(loginUser.pending, (state) => {
                state.value.status = 'loading';
            })
            .addCase(loginUser.rejected, (state, action) => {
                state.value.status = 'failed';
                state.value.error = action.error.message;
            })
            .addCase(checkSession.pending, (state) => {
                state.value.status = 'loading';
            })
            .addCase(checkSession.rejected, (state, action) => {
                state.value.status = 'failed';
                state.value.error = action.error.message;
            });
    },
});

// Action creators are generated for each case reducer function
export const { login, setCurrentUser, logout, setLoginStatus } = currentUser.actions;

export default currentUser.reducer;

const fetchLoginUser = async (credentials) => {
    let apiRoute = `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/auth`;
    let token = null;

    try {
        const response = await axios.post(apiRoute, credentials);
        token = response.data;
    } catch (error) {
        // console.error('Error trying to login:', error.message);
    }

    // console.log('Token:', token);

    const decoded = jwtDecode(token);
    // console.log('Decoded token:', decoded);

    apiRoute = `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/auth/credentials/${decoded.id}`;
    const userEmail = decoded.email;

    let userData = null;

    try {
        const response = await axios.post(apiRoute, { email: userEmail},{
            headers: {
                Authorization: `Bearer ${token}`,
                'Access-Control-Allow-Origin': '*',
            },
        });
        userData = response.data;
    } catch (error) {
        // console.error('Error trying to fetch user data:', error.message);
    }

    // console.log('User data:', userData);

    return {
        user: userData,
        token: token,
    };
};
