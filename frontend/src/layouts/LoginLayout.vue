<template>
  <q-layout view="hHh lpR fFf">
    <q-page-container>
      <q-page>
        <div
          class="fullscreen custom-background row justify-center items-center content-center"
        >
          <q-card class="q-pa-md">
            <q-tabs v-model="tab" class="text-teal" align="justify">
              <q-tab name="login" label="Giriş Ekranı" />
              <q-tab name="register" label="Kayıt Ol" />
            </q-tabs>

            <q-tab-panels v-model="tab" animated>
              <q-tab-panel name="login">
                <q-card-section>
                  <div class="text-h6">Giriş Ekranı</div>
                </q-card-section>
                <div class="q-gutter-md">
                  <q-input
                    label="Kullanıcı Adı"
                    outlined
                    v-model="kullaniciAdi"
                  ></q-input>

                  <q-input
                    v-model="password"
                    outlined
                    label="Şifre"
                    :type="isPwd ? 'password' : 'text'"
                  >
                    <template v-slot:append>
                      <q-icon
                        :name="isPwd ? 'visibility_off' : 'visibility'"
                        class="cursor-pointer"
                        @click="isPwd = !isPwd"
                      ></q-icon>
                    </template>
                  </q-input>

                  <q-separator />

                  <div class="q-pa-md q-gutter-sm row justify-center q-mt-none">
                    <q-btn
                      color="white"
                      text-color="black"
                      label="GİRİŞ"
                      @click="login()"
                      :disabled="!kullaniciAdi || !password"
                    ></q-btn>
                  </div>
                </div>
              </q-tab-panel>

              <q-tab-panel name="register">
                <q-card-section>
                  <div class="text-h6">Kayıt Ol</div>
                </q-card-section>
                <div class="q-gutter-md">
                  <q-input
                    label="Yeni Kullanıcı Adı"
                    outlined
                    v-model="newKullaniciAdi"
                  ></q-input>

                  <q-input
                    label="Yeni Şifre"
                    outlined
                    v-model="newPassword"
                    :type="isNewPwd ? 'password' : 'text'"
                  >
                    <template v-slot:append>
                      <q-icon
                        :name="isNewPwd ? 'visibility_off' : 'visibility'"
                        class="cursor-pointer"
                        @click="isNewPwd = !isNewPwd"
                      ></q-icon>
                    </template>
                  </q-input>

                  <q-separator />

                  <div class="q-pa-md q-gutter-sm row justify-center">
                    <q-btn
                      color="primary"
                      label="Kayıt Ol"
                      @click="registerUser"
                    ></q-btn>
                  </div>
                </div>
              </q-tab-panel>
            </q-tab-panels>
          </q-card>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useQuasar } from "quasar";
import axios from "axios"; // Axios import

const tab = ref("login");
const kullaniciAdi = ref("");
const password = ref("");
const isPwd = ref(true);

const newKullaniciAdi = ref("");
const newPassword = ref("");
const isNewPwd = ref(true);

const $q = useQuasar();

async function login() {
  try {
    const payload = {
      kullaniciAdi: kullaniciAdi.value,
      sifre: password.value,
    };
    const resp = await axios.post("/api/auth/login", payload);
    console.log(resp.data);
    $q.notify({
      color: "positive",
      message: "Giriş başarılı!",
      position: "center",
    });
    // Giriş başarılı ise yönlendirme yapılır
  } catch (error) {
    console.error("Giriş hatası:", error);
    $q.notify({
      color: "negative",
      message:
        "Giriş başarısız! Lütfen kullanıcı adı ve şifrenizi kontrol edin.",
      position: "center",
    });
  }
}
async function registerUser() {
  if (!newKullaniciAdi.value || !newPassword.value) {
    $q.notify({
      color: "negative",
      message: "Lütfen kullanıcı adı ve şifre giriniz.",
      position: "center",
    });
    return;
  }

  try {
    const payload = {
      username: newKullaniciAdi.value,
      password: newPassword.value,
    };

    const response = await axios.post(
      "http://localhost:8081/api/auth/createUser",
      payload
    );

    console.log("Kayıt işlemi başarılı:", response.data);
    $q.notify({
      color: "positive",
      message: "Başarıyla kayıt oldunuz!",
      position: "center",
    });

    // Başarıyla kayıt olduktan sonra gerekli işlemleri yapabilirsiniz
    newKullaniciAdi.value = "";
    newPassword.value = "";
  } catch (error) {
    console.error("Kayıt hatası:", error);
    $q.notify({
      color: "negative",
      message: "Kayıt işlemi başarısız oldu. Lütfen tekrar deneyin.",
      position: "center",
    });
  }
}
</script>

<style>
.custom-background {
  background-color: #ffebee;
}
</style>
