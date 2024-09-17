<template>
  <q-page class="q-pa-md bg-pink-1">
    <q-list>
      <q-item
        v-for="topic in topicsList"
        :key="topic.title"
        @click="goToChat(topic)"
        clickable
        class="text-white"
      >
        <q-item-section>
          <q-item-label>{{ topic.title }}</q-item-label>
        </q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script lang="ts" setup>
import { useRouter } from "vue-router";

interface Topic {
  title: string;
  link: string;
}

const router = useRouter();
const topicsList: Topic[] = [
  { title: "General Chat", link: "/main/chat/general-chat" },
  // diğer konular...
];

function goToChat(topic: Topic) {
  // 'chatId' parametresi ile URL'yi güncelle
  router.push({
    path: "/main/chat",
    query: { chatId: topic.title.toUpperCase().replace(/ /g, "-") },
  });
}
</script>
