import { RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    component: () => import("layouts/LoginLayout.vue"),
  },
  {
    path: "/main",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "topics",
        component: () => import("layouts/TopicsList.vue"),
      },
      {
        path: "chat/:topicId",
        name: "chat-page",
        component: () => import("pages/ChatPage.vue"),
        props: (route) => ({ topicId: route.params.topicId }),
      },
    ],
  },
  // Always leave this as the last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("layouts/ErrorNotFound.vue"),
  },
];

export default routes;
