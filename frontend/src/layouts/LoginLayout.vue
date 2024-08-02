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
                      @click="login"
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

          <!-- Dialog bileşeni -->
          <q-dialog v-model="dialogVisible">
            <q-card>
              <q-card-section>
                <div class="text-h6">{{ dialogTitle }}</div>
                <p>{{ dialogMessage }}</p>
              </q-card-section>

              <q-card-actions>
                <q-btn flat label="Tamam" @click="closeDialog" />
              </q-card-actions>
            </q-card>
          </q-dialog>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useQuasar } from "quasar";
import { api } from "src/boot/axios";

const $q = useQuasar();

const tab = ref("login");
const kullaniciAdi = ref("");
const password = ref("");
const isPwd = ref(true);

const newKullaniciAdi = ref("");
const newPassword = ref("");
const isNewPwd = ref(true);

// Dialog durumları
const dialogVisible = ref(false);
const dialogTitle = ref("");
const dialogMessage = ref("");

async function login() {
  try {
    const payload = {
      kullaniciAdi: kullaniciAdi.value,
      sifre: password.value,
    };
    const resp = await api.post("/auth/login", payload);
    const token = resp.data;
    $q.localStorage.set("token", token);

    dialogTitle.value = "Başarı";
    dialogMessage.value = "Giriş başarılı!";
    dialogVisible.value = true;
  } catch (error) {
    console.error("Giriş hatası:", error);

    // Hata türünü kontrol et
    if (error instanceof Error) {
      const errorResponse = error as {
        response?: { status?: number; data?: { message?: string } };
      };
      const status = errorResponse.response?.status;
      const message =
        errorResponse.response?.data?.message || "Giriş başarısız oldu.";

      dialogTitle.value = "Hata";
      dialogMessage.value =
        status === 401 ? "Geçersiz kullanıcı adı veya şifre." : message;
    } else {
      dialogTitle.value = "Hata";
      dialogMessage.value = "Bilinmeyen bir hata oluştu.";
    }
    dialogVisible.value = true;
  }
}

async function registerUser() {
  if (!newKullaniciAdi.value || !newPassword.value) {
    dialogTitle.value = "Hata";
    dialogMessage.value = "Lütfen kullanıcı adı ve şifre giriniz.";
    dialogVisible.value = true;
    return;
  }

  try {
    const payload = {
      username: newKullaniciAdi.value,
      password: newPassword.value,
    };

    await api.post("/auth/createUser", payload);

    dialogTitle.value = "Başarı";
    dialogMessage.value = "Başarıyla kayıt oldunuz!";
    dialogVisible.value = true;

    newKullaniciAdi.value = "";
    newPassword.value = "";
  } catch (error) {
    console.error("Kayıt hatası:", error);
    dialogTitle.value = "Hata";
    dialogMessage.value = "Kayıt işlemi başarısız oldu. Lütfen tekrar deneyin.";
    dialogVisible.value = true;
  }
}

function closeDialog() {
  dialogVisible.value = false;
}
</script>

<style>
.custom-background {
  background-color: #ffebee;
}
</style>
