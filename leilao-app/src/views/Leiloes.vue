<template>
  <v-container>
    <v-card>
      <v-card-title>
        Leiloes
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col>
            <v-text-field label="Razão Social" v-model="filtro.razaoSocial"></v-text-field>
          </v-col>
          <v-col class="col-4">
            <v-text-field type="date" label="Início Previsto" v-model="filtro.inicioPrevisto"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="gray" class="margin-l-10" @click="limpar">Limpar</v-btn>
              <v-btn color="blue" class="margin-l-10" @click="obterPorFiltro">Pesquisar</v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <Tabela :cabecalho='cabecalho' :itens='leiloes'></Tabela>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>
<script>
import Tabela from "@/components/Tabela.vue";
import api from '../services/leiloes';

export default {
  name: 'Leiloes',
  components: {Tabela},

  data: () => ({

    editedIndex: -1,
    cabecalho: [
      { text: 'Razao Social', value: 'razaoSocial' },
      { text: 'Início Previsto', value: 'data' },
      { text: 'Total', value: 'total' },
    ],
    leiloes: [],
    filtro: {
      inicioPrevisto: null,
      razaoSocial: null
    }

  }),

  methods: {

    async obterPorFiltro() {
      await api.get('/filtro', { params: this.filtro }).then((resposta) => {
        this.leiloes = resposta.data
      });
    },

    limpar() {
      this.filtro = {
        inicioPrevisto: null,
        razaoSocial: null
      }
      this.obterPorFiltro()
    },

  },

  mounted () {
    this.obterPorFiltro()
  }

}
</script>
