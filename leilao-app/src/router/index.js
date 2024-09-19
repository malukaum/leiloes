import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Início',
    components: {
      default: () => import('../views/Inicio.vue'),
      sidebar: () => import('../components/Navbar.vue')
    }
  },
  {
    path: '/unidades',
    name: 'Unidades',
    components: {
      default: () => import('../views/Unidades.vue'),
      sidebar: () => import('../components/Navbar.vue')
    }
  },
  {
    path: '/leiloes',
    name: 'Leilões',
    components: {
      default: () => import('../views/Leiloes.vue'),
      sidebar: () => import('../components/Navbar.vue')
    }
  },
  {
    path: '/empresas',
    name: 'Empresas',
    components: {
      default: () => import('../views/Empresas.vue'),
      sidebar: () => import('../components/Navbar.vue')
    }
  },
  {
    path: '/empresa',
    name: 'Empresa',
    components: {
      default: () => import('../views/Empresa.vue'),
      sidebar: () => import('../components/Navbar.vue')
    }
  },
  {
    path: '/empresa/:id',
    name: 'Empresa',
    components: {
      default: () => import('../views/Empresa.vue'),
      sidebar: () => import('../components/Navbar.vue')
    }
  }
]

const router = new VueRouter({
  routes
})

export default router
