<template>
  <q-page class="q-pa-md">
    <q-list>
      <q-item
        v-for="topic in topics"
        :key="topic.id"
        clickable
        @click="goToChat(topic.id)"
      >
        <q-item-section>{{ topic.title }}</q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script lang="ts" setup>
import { useRouter } from "vue-router";

const router = useRouter();

const topics = [
  { id: "general-chat", title: "General Chat" },
  { id: "technology", title: "Technology" },
  { id: "news", title: "News" },
  { id: "literature", title: "Literature" },
  { id: "life", title: "Life" },
  { id: "swimming", title: "Swimming" },
];

function goToChat(topicId: string) {
  router.push({ name: "chat-page", params: { topicId } });
}
</script>
