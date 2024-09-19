<template>
  <v-container>
    <v-card>
      <v-card-title>
        Empresas
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col class="col-4">
            <v-text-field v-mask="mascaraCnpj" label="CNPJ" v-model="filtro.cnpj"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field label="Razão Social" v-model="filtro.razaoSocial"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="col-4">
            <v-text-field v-mask="mascaraTelefone" label="Telefone" v-model="filtro.telefone"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field label="E-mail" v-model="filtro.email"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="gray" class="margin-l-10" @click="limpar">Limpar</v-btn>
              <v-btn color="blue" class="margin-l-10" @click="obterPorFiltro">Pesquisar</v-btn>
              <v-btn color="green" class="margin-l-10" @click="inserirNovo()">Inserir novo</v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <Tabela :cabecalho='cabecalho' :itens='itens' @deletarItem="deletarItem" @editar="editar"></Tabela>
            <v-dialog v-model="dialogoDeletar" max-width="500px">
              <v-card>
                <v-card-title class="text-h5">Tem certeza que deseja deletar o item?</v-card-title>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="gray" variant="text" @click="cancelarDeletar">Cancelar</v-btn>
                  <v-btn color="green" variant="text" @click="confirmarDeletar">Confirmar</v-btn>
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>

      </v-card-text>
      <v-snackbar :timeout="2000" :color="severidade" v-model="snackbar">{{ mensagem }}</v-snackbar>
    </v-card>
  </v-container>
</template>
<script lang="ts">

import api from '../services/empresas';
import Tabela from "@/components/Tabela.vue";

export default {

  name: 'Empresas',
  components: {Tabela},

  data: () => ({

    dialogoDeletar: false,
    editedIndex: -1,
    cabecalho: [
      { text: 'CNPJ', value: 'cnpj' },
      { text: 'Razão Social', value: 'razaoSocial' },
      { text: 'Telefone', value: 'telefone' },
      { text: 'Email', value: 'email' },
      { text: 'Ações', value: 'actions', sortable: false, width: '100px' }
    ],
    itens: [],
    mascaraCnpj: "##.###.###/####-##",
    mascaraCep: "##.###-###",
    filtro: {
      cnpj: null,
      razaoSocial: null,
      telefone: null,
      email: null
    },
    snackbar: false,
    mensagem: "",
    severidade: "green"

  }),

  methods: {

    async obterPorFiltro() {
      await api.get('/filtro', { params: this.filtro }).then((response) => {
        this.itens = response.data;
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao filtrar os dados!"
      });
    },

    async excluir(id) {
      await api.delete(`/${id}`).then(() => {
        this.snackbar = true;
        this.severidade = "green";
        this.mensagem = "Dados excluídos com sucesso."
        this.obterPorFiltro()
        this.cancelarDeletar()
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao excluir dados!"
        this.cancelarDeletar()
      });
    },

    editar(item) {
      this.$router.push(`/empresa/${item.id}`)
    },

    deletarItem(item) {
      this.editedIndex = this.itens.indexOf(item)
      this.dialogoDeletar = true
    },

    confirmarDeletar() {
      this.excluir(this.itens[this.editedIndex].id)
    },

    cancelarDeletar () {
      this.dialogoDeletar = false
      this.$nextTick(() => {
        this.editedIndex = -1
      })
    },

    inserirNovo(){
      this.editedIndex = -1;
      this.$router.push('/empresa')
    },

    limpar() {
      this.filtro = {
        cnpj: null,
        razaoSocial: null,
        telefone: null,
        email: null
      }
      this.obterPorFiltro()
    }

  },

  watch: {
    dialogoCadastro (val) {
      val || this.close()
    },
  },

  mounted () {
    this.obterPorFiltro()
  },

  computed: {
    mascaraTelefone() {
      if (!this.filtro.telefone || this.filtro.telefone.length < 15) {
        return "(##) ####-####"
      } else {
        return "(##) #####-####"
      }
    },
  }

}

</script>
