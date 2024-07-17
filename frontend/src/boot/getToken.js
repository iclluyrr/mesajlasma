const axios = require("axios"); //baska bir dosyadan veri alma
const qs = require("querystring");

async function getToken(username, password) {
  try {
    const data = qs.stringify({
      client_id: "spring-server",
      username: "bbb",
      password: "bbb",
      grant_type: password,
    });

    const config = {
      method: "post",
      url: "http://localhost:8080/realms/mesajlasma/protocol/openid-connect/token",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      data: data,
    };

    const response = await axios(config); // await işlem tamamlanana kadar diğer kodların çalışmasını duraklatır
    console.log(JSON.stringify(response.data)); //veriyi JSON formatına dönüştürerek bir metin olarak yazdırır . response.data gelen cevabı içerir
    return response.data.access_token;
  } catch (error) {
    console.error("Login failed:", error.message);
    throw error; //hatayı yeniden fırlatır
  }
}

// Kullanım örneği:
getToken("bbb", "bbb")
  .then((token) => {
    console.log("Access Token:", token);
  })
  .catch((err) => {
    console.error("Failed to get token:", err);
  });
