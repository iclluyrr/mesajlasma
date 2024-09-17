import { boot } from "quasar/wrappers";
import axios, { AxiosInstance } from "axios";

declare module "@vue/runtime-core" {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
    $api: AxiosInstance;
  }
}

const api = axios.create({ baseURL: "http://localhost:8081/api" });

// Add a request interceptor
api.interceptors.request.use(
  (config) => {
    // Get the token from localStorage

    // If the token exists, add it to the headers
    const token = localStorage.getItem("token");

    // Token'ın başındaki __q_strn| ifadesini çıkartın
    const cleanToken = token ? token.replace(/^__q_strn\|/, "") : "";

    // Token'ı Authorization başlığına ekleyin
    if (cleanToken && config.url !== "/auth/login") {
      config.headers.Authorization = `Bearer ${cleanToken}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default boot(({ app }) => {
  app.config.globalProperties.$axios = axios;
  app.config.globalProperties.$api = api;
});

export { api, axios };
