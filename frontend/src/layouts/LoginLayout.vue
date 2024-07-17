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
                      />
                    </template>
                  </q-input>

                  <q-separator />

                  <div class="q-pa-md q-gutter-sm row justify-center q-mt-none">
                    <q-btn color="white" text-color="black" label="GİRİŞ" />
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
                      />
                    </template>
                  </q-input>

                  <q-separator />

                  <div class="q-pa-md q-gutter-sm row justify-center">
                    <q-btn
                      color="primary"
                      label="Kayıt Ol"
                      @click="registerUser"
                    />
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
import axios from "axios";

const tab = ref("login");
const kullaniciAdi = ref("");
const password = ref("");
const isPwd = ref(true);

const newKullaniciAdi = ref("");
const newPassword = ref("");
const isNewPwd = ref(true);

const $q = useQuasar();

function registerUser() {
  if (!newKullaniciAdi.value || !newPassword.value) {
    //alert("Lütfen kullanıcı adı ve şifre giriniz.");
    $q.notify({
      color: "negative",
      message: "Lütfen kullanıcı adı ve şifre giriniz.",
      position: "center",
    });
    return;
  }

  alert("Başarıyla kayıt oldunuz!");

  newKullaniciAdi.value = "";
  newPassword.value = "";
}
</script>

<style>
.custom-background {
  background-color: #ffebee;
}
</style>
