import { boot } from "quasar/wrappers";
import axios, { AxiosInstance } from "axios";
export { api };

import qs from "qs";

declare module "@vue/runtime-core" {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
    $api: AxiosInstance;
  }
}

// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)
const api = axios.create({ baseURL: "https://api.example.com" });

export default boot(({ app }) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios;
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api;
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
});

const data = qs.stringify({
  // data değişkenini hazırlar
  client_id: "spring-server",
  username: "bbb",
  password: "bbb",
  grant_type: "password",
});

const config = {
  method: "post",
  maxBodyLength: Infinity, // sınırsız
  url: "http://localhost:8080/realms/mesajlasma/protocol/openid-connect/token",
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
  },
  data: data, // gönderilecek veri yukarıda hazırlanan data
};

axios
  .request(config) // isteği gönderme
  .then((response) => {
    // basarılı cevap gelirse
    console.log(JSON.stringify(response.data)); // cevabı json formatına dönüştürüp konsola yazdırır
    if (typeof window !== "undefined") {
      // eğer tarayıcı ortamında isek
      alert(`Token: ${response.data.access_token}`); // kullanıcıya tokeni alert ile gösterme
    }
  })
  .catch((error) => {
    // hata durumunda
    console.error(error); // hatanın detaylarını konsola yazdırma
    if (typeof window !== "undefined") {
      // eğer tarayıcı ortamındaysak
      alert("Login failed"); // kullnıcıya giriş başarısız mesajını gösterir.
    }
  });
