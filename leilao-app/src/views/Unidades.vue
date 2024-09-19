<template>
  <v-container><v-card>
    <v-card-title>
      Unidades
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-col>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green" class="margin-l-10" @click="inserirNovo()">Inserir novo</v-btn>
          </v-card-actions>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <Tabela :cabecalho='cabecalho' :itens='itens' @deletarItem="deletarItem" @editar="editar"></Tabela>
          <v-dialog v-model="dialogoCadastro" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ titulo }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col>
                      <v-text-field v-model="nome" label="Nome"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="gray" variant="text" @click="close">
                  Cancelar
                </v-btn>
                <v-btn color="green" variant="text" @click="persistir">
                  Salvar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
    <v-snackbar :color="severidade" v-model="snackbar">{{ mensagem }}</v-snackbar>
  </v-card>
  </v-container>
</template>
<script>
import api from '../services/unidades';
import Tabela from "@/components/Tabela.vue";

export default {

  name: 'Unidades',
  components: {Tabela},

  data: () => ({

    dialogoCadastro: false,
    dialogoDeletar: false,
    editedIndex: -1,
    cabecalho: [
      { text: 'Nome', value: 'nome' },
      { text: 'Ações', value: 'actions', sortable: false, width: '100px' }
    ],
    itens: [],
    nome: "",
    snackbar: false,
    mensagem: "",
    severidade: "green"
  }),

  methods: {

    async obterUnidades() {
      await api.get('').then((response) => {
        this.itens = response.data;
      });
    },

    async salvarItem() {
      await api.post('', { nome: this.nome }).then(() => {
        this.snackbar = true;
        this.severidade = "green";
        this.mensagem = "Dados salvos com sucesso."
        this.obterUnidades()
        this.nome = ""
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao salvar dados!"
      });
    },

    async excluir(id) {
      await api.delete(`/${id}`).then(() => {
        this.snackbar = true;
        this.severidade = "green";
        this.mensagem = "Dados excluídos com sucesso."
        this.obterUnidades()
        this.cancelarDeletar()
      }).catch(() => {
        this.cancelarDeletar()
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao exlcuir dados!"
      });
    },

    async editarItem() {
      await api.put(`/${this.itens[this.editedIndex].id}`, {nome: this.nome}).then(() => {
        this.snackbar = true;
        this.severidade = "green";
        this.mensagem = "Dados alterados com sucesso."
        this.dialogoCadastro = false;
        this.obterUnidades()
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao editar dados!"
      });
    },

    close () {
      this.dialogoCadastro = false
      this.$nextTick(() => {
        this.nome = ""
        this.editedIndex = -1
      })
    },

    persistir() {
      if (this.editedIndex >= 0) {
        this.editarItem();
      } else {
        this.salvarItem();
      }
      this.dialogoCadastro = false;
    },

    editar(item) {
      this.editedIndex = this.itens.indexOf(item)
      this.nome = item.nome
      this.dialogoCadastro = true
    },

    deletarItem(item) {
      this.editedIndex = this.itens.indexOf(item)
      this.nome = ""
      this.dialogoDeletar = true
    },

    confirmarDeletar() {
      this.excluir(this.itens[this.editedIndex].id)
    },

    cancelarDeletar () {
      this.dialogoDeletar = false
      this.$nextTick(() => {
        this.nome = ""
        this.editedIndex = -1
      })
    },

    inserirNovo(){
      this.editedIndex = -1;
      this.nome = "";
      this.dialogoCadastro = true;
    }

  },

  watch: {
    dialogoCadastro (val) {
      val || this.close()
    },
  },

  mounted () {
    this.obterUnidades()
  },

  computed: {
    titulo () {
      return this.editedIndex === -1 ? 'Nova Unidade' : 'Editar Unidade'
    },
  },

}

</script>
