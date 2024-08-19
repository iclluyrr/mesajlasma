<template>
  <q-layout view="lHh Lpr lFf" class="bg-pink-2 text-white">
    <!-- Header -->
    <q-header elevated class="bg-pink-3">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="toggleLeftDrawer"
          aria-label="Menu"
          icon="menu"
          class="text-white"
        />
        <q-toolbar-title class="text-white">
          <q-icon name="chat" /> Chat App
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <!-- Drawer -->
    <q-drawer v-model="leftDrawerOpen" show-if-above class="bg-pink-4">
      <q-list dark>
        <!-- Essential Links Section -->
        <q-item-label header class="text-white">Essential Links</q-item-label>
        <q-item
          clickable
          target="_blank"
          rel="noopener"
          href="https://quasar.dev"
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="school" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">Docs</q-item-label>
            <q-item-label caption class="text-white">
              https://quasar.dev
            </q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          clickable
          target="_blank"
          rel="noopener"
          href="https://github.quasar.dev"
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="code" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">GitHub</q-item-label>
            <q-item-label caption class="text-white">
              github.com/quasarframework
            </q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          clickable
          target="_blank"
          rel="noopener"
          href="http://chat.quasar.dev"
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="chat" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">Discord Chat Channel</q-item-label>
            <q-item-label caption class="text-white">
              https://chat.quasar.dev
            </q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          clickable
          target="_blank"
          rel="noopener"
          href="https://forum.quasar.dev"
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="record_voice_over" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">Forum</q-item-label>
            <q-item-label caption class="text-white">
              https://forum.quasar.dev
            </q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          clickable
          target="_blank"
          rel="noopener"
          href="https://twitter.quasar.dev"
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="rss_feed" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">Twitter</q-item-label>
            <q-item-label caption class="text-white">
              @quasarframework
            </q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          clickable
          target="_blank"
          rel="noopener"
          href="https://facebook.quasar.dev"
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="public" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">Facebook</q-item-label>
            <q-item-label caption class="text-white">
              @QuasarFramework
            </q-item-label>
          </q-item-section>
        </q-item>

        <!-- Topics List Section -->
        <q-item-label header class="q-mt-md text-white"
          >Topics List</q-item-label
        >
        <q-item
          v-for="topic in topicsList"
          :key="topic.title"
          @click="goToChat(topic)"
          :active="selectedTopic === topic"
          clickable
          class="text-white"
        >
          <q-item-section avatar>
            <q-icon name="chat" class="text-white" />
          </q-item-section>
          <q-item-section>
            <q-item-label class="text-white">{{ topic.title }}</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <!-- Main Page Content -->
    <q-page-container>
      <q-page class="q-pa-md bg-pink-1">
        <router-view />
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

interface Topic {
  title: string;
  link: string;
}

const router = useRouter();
const leftDrawerOpen = ref(false);
const selectedTopic = ref<Topic | null>(null);

const topicsList: Topic[] = [
  { title: "General Chat", link: "/main/chat/general-chat" },
  { title: "Technology", link: "/main/chat/technology" },
  { title: "News", link: "/main/chat/news" },
  { title: "Literature", link: "/main/chat/literature" },
  { title: "Life", link: "/main/chat/life" },
  { title: "Swimming", link: "/main/chat/swimming" },
];

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}

function goToChat(topic: Topic) {
  router.push({ path: topic.link });
}
</script>

<style scoped>
.q-drawer {
  max-width: 300px;
}

.q-page {
  background-color: #fce4ec;
}

.bg-pink-1 {
  background-color: #f8bbd0; /* Very light pink */
}

.bg-pink-2 {
  background-color: #f48fb1; /* Light pink */
}

.bg-pink-3 {
  background-color: #ec407a; /* Medium pink */
}

.bg-pink-4 {
  background-color: #d81b60; /* Dark pink */
}

.text-white {
  color: #ffffff; /* White color for text */
}
</style>
