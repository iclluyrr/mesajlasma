<template>
  <q-page class="q-pa-md bg-pink-1">
    <div v-if="chatId">
      <h2 class="text-h5">Chat Room: {{ chatId }}</h2>
      <div class="chat-container">
        <q-scroll-area class="chat-messages">
          <div
            v-for="message in messages"
            :key="message.id"
            class="chat-message"
          >
            <q-item>
              <q-item-section>
                <q-item-label class="message-sender">
                  {{ message.sender }}
                </q-item-label>
                <q-item-label class="message-timestamp">
                  {{ formatTimestamp(message.timestamp) }}
                </q-item-label>
                <q-item-label>{{ message.icerik }}</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-scroll-area>
        <div class="chat-input">
          <q-input
            v-model="newMessage"
            label="Type your message..."
            @keyup.enter="sendMessage"
            dense
            outlined
            autofocus
          />
          <q-btn @click="sendMessage" label="Send" color="primary" />
        </div>
        <q-banner v-if="error" class="bg-red text-white">
          {{ error }}
        </q-banner>
      </div>
    </div>
    <div v-else>
      <p>Lütfen listeden bir sohbet konusu seçin.</p>
    </div>
  </q-page>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { api } from "src/boot/axios";

interface Message {
  id: string;
  sender: string;
  icerik: string;
  timestamp: Date;
}

// Route query'den chatId'yi almak
const route = useRoute();
const chatId = ref<string | undefined>(
  route.query.chatId as string | undefined
);
const messages = ref<Message[]>([]);
const newMessage = ref<string>("");
const error = ref<string | null>(null);

// Mevcut mesajları backend'den al
onMounted(async () => {
  if (chatId.value) {
    try {
      const response = await api.get(`/messages/topic/${chatId.value}`);
      messages.value = response.data;
    } catch (err) {
      error.value = "Mesajlar alınırken hata oluştu.";
      console.error("Mesajlar alınırken hata oluştu:", err);
    }
  }
});

function sendMessage() {
  if (newMessage.value.trim()) {
    // Yeni mesajı backend'e gönder
    api
      .post("/messages", {
        baslik: "Başlık", // Eğer dinamik bir başlık varsa buraya ekleyin
        icerik: newMessage.value.trim(),
        topicId: "30c23a24-e78e-4f8b-b321-c5f694547ed7",
      })
      .then((response) => {
        messages.value.push(response.data);
        newMessage.value = ""; // Mesaj alanını temizle
        error.value = null; // Hata varsa temizle
      })
      .catch((err) => {
        error.value = "Mesaj gönderilirken hata oluştu.";
        console.error("Mesaj gönderilirken hata oluştu:", err);
      });
  } else {
    error.value = "Mesaj boş olamaz.";
  }
}

function formatTimestamp(date: Date): string {
  const options: Intl.DateTimeFormatOptions = {
    weekday: "short",
    year: "numeric",
    month: "short",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
  };
  return new Date(date).toLocaleDateString("tr-TR", options);
}
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  margin-bottom: 10px;
}

.chat-message {
  margin-bottom: 10px;
}

.message-sender {
  font-weight: bold;
}

.message-timestamp {
  font-size: 0.8em;
  color: #888;
}

.chat-input {
  display: flex;
  align-items: center;
  gap: 10px;
}

.q-banner {
  margin-top: 10px;
}
</style>
