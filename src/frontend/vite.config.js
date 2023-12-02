import { defineConfig } from 'vite'
import jsconfigPaths from 'vite-jsconfig-paths'
import react from '@vitejs/plugin-react'
// import envCompatible from 'vite-plugin-env-compatible'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react({
      include: "**/*.jsx",
    }),
    jsconfigPaths(),
    // envCompatible({
    //   API_TYPE: process.env.NODE_ENV === 'json' ? 'json' :
    //     process.env.NODE_ENV === 'spring' ? 'spring' : '',
    // })
  ],
  define: {
    'process.env': {
      API_TYPE: process.env.API_TYPE === 'json' ? 'json' :
        process.env.API_TYPE === 'spring' ? 'spring' : '',
    }
  }
});
