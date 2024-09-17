import { RouteRecordRaw } from "vue-router";
const routes: RouteRecordRaw[] = [
  {
    path: "/",
    component: () => import("layouts/LoginLayout.vue"),
    // Burada, bir alt rota tanımlamanız gerekiyorsa veya giriş yapmış kullanıcıyı kontrol etmek istiyorsanız, ek konfigürasyon yapabilirsiniz.
  },
  {
    path: "/main",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "topics",
        name: "topics-list",
        component: () => import("pages/TopicsList.vue"),
      },
      {
        path: "chat",
        name: "chat-page",
        component: () => import("pages/ChatPage.vue"),
      },
    ],
  },
  {
    path: "/:catchAll(.*)*",
    component: () => import("layouts/ErrorNotFound.vue"),
  },
];

export default routes;
