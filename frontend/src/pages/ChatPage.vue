<template>
  <q-page class="q-pa-md bg-pink-1">
    <q-card v-if="selectedTopic" class="q-pa-md bg-white" flat bordered>
      <q-card-section>
        <div class="text-h6 text-pink-5">{{ selectedTopic.title }}</div>

        <!-- Mesaj Listesi -->
        <div
          v-for="message in messages"
          :key="message.timestamp"
          class="q-mb-md"
        >
          <div class="text-caption text-pink-5">
            {{ message.sender }}: {{ message.timestamp }}
          </div>
          <div class="text-pink-5">{{ message.text }}</div>
        </div>

        <!-- Mesaj Girişi -->
        <q-input
          v-model="newMessage"
          filled
          placeholder="Mesajınızı yazın..."
          @keyup.enter="sendMessage"
          dense
          class="text-pink-5"
        >
          <template v-slot:append>
            <q-btn icon="send" @click="sendMessage" class="text-pink-5" />
          </template>
        </q-input>
      </q-card-section>
    </q-card>
    <q-card v-else class="q-pa-md bg-white">
      <q-card-section>
        <div class="text-h6 text-pink-5">Bir Konu Seçin</div>
        <p class="text-pink-5">
          Konulardan birini seçerek sohbet etmeye başlayın.
        </p>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script lang="ts" setup>
import { ref, watchEffect } from "vue";
import { useRoute } from "vue-router";

interface Topic {
  title: string;
  link: string;
}

interface Message {
  sender: string;
  text: string;
  timestamp: string;
}

const route = useRoute();
const selectedTopic = ref<Topic | null>(null);
const newMessage = ref("");
const messages = ref<Message[]>([]);

watchEffect(() => {
  const topicId = route.params.topicId as string;

  const topicData: { [key: string]: Topic } = {
    "general-chat": { title: "General Chat", link: "" },
    technology: { title: "Technology", link: "" },
    news: { title: "News", link: "" },
    literature: { title: "Literature", link: "" },
    life: { title: "Life", link: "" },
    swimming: { title: "Swimming", link: "" },
  };
  selectedTopic.value = topicData[topicId] || null;
});

function sendMessage() {
  if (newMessage.value.trim()) {
    const timestamp = new Date().toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
    });
    messages.value.push({
      sender: "Sen",
      text: newMessage.value,
      timestamp: timestamp,
    });
    newMessage.value = "";
  }
}
</script>

<style scoped>
.bg-pink-1 {
  background-color: #f8bbd0; /* Very light pink */
}

.text-pink-5 {
  color: #f50057; /* Vivid pink */
}
</style>
