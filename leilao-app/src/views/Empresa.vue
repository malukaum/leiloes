<template>
  <v-container>
    <v-card>
      <v-card-title>
        {{ titulo }}
      </v-card-title>
      <v-card-text>
        <v-form ref="form">
          <v-row>
            <v-col class="col-4">
              <v-text-field counter="64" :rules="[rules.required]" v-mask="[mascaraCnpj]" label="CNPJ" v-model="empresa.cnpj"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field counter="32" :rules="[rules.required]" label="Razão Social" v-model="empresa.razaoSocial"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field counter="64" label="Logradouro" v-model="empresa.logradouro"></v-text-field>
            </v-col>
            <v-col class="col-4">
              <v-text-field counter="10" label="Numero" v-model="empresa.numero"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field counter="64" label="Complemento" v-model="empresa.complemento"></v-text-field>
            </v-col>
            <v-col class="col-4">
              <v-text-field counter="16" v-mask="[mascaraCep]" label="CEP" v-model="empresa.cep"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field counter="64" label="Bairro" v-model="empresa.bairro"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field counter="64" label="Municipio" v-model="empresa.municipio"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field counter="32" v-mask="[mascaraTelefone]" label="Telefone" v-model="empresa.telefone"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field counter="254" :rules="[rules.required, rules.email]" label="Email" v-model="empresa.email"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field counter="254" label="Site" v-model="empresa.site"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field counter="20" :rules="[rules.required]" label="Usuario" v-model="empresa.usuario"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field counter="128" type="password" label="Senha" v-model="empresa.senha"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field counter="128" :rules="validarSenhasIguais" type="password" label="Repetir Senha" v-model="empresa.rSenha"></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="gray" variant="text" @click="cancelar">
          Cancelar
        </v-btn>
        <v-btn color="green" variant="text" @click="persistir">
          Salvar
        </v-btn>
      </v-card-actions>
      <v-snackbar :timeout="2000" :color="severidade" v-model="snackbar">{{ mensagem }}</v-snackbar>
    </v-card>
  </v-container>
</template>
<script lang="ts">

import api from '../services/empresas';

export default {

  name: 'Empresa',

  data: () => ({

    dialogoDeletar: false,
    empresa: {
      id: null,
      cnpj: null,
      razaoSocial: null,
      logradouro: null,
      numero: null,
      complemento: null,
      bairro: null,
      municipio: null,
      cep: null,
      telefone: null,
      email: null,
      site: null,
      usuario: null,
      senha: null,
      rSenha: null
    },
    mascaraCnpj: "##.###.###/####-##",
    mascaraCep: "##.###-###",
    mascaraTelefone: "(##) #####-####",
    snackbar: false,
    mensagem: "",
    severidade: "green",
    rules: {
      required: value => !!value || 'Campo obrigatório.',
      email: value => {
        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        return pattern.test(value) || 'Email inválido.'
      },
    },

  }),

  methods: {

    async salvarItem() {
      await api.post('', this.empresa).then(() => {
        this.snackbar = true;
        this.severidade = "green";
        this.mensagem = "Dados salvo com sucesso."
        this.empresa = this.empresaVazia()
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao salvar dados!"
      });
    },

    async editarItem() {
      await api.put(`/${this.empresa.id}`, this.empresa).then(() => {
        this.snackbar = true;
        this.severidade = "green";
        this.mensagem = "Dados alterados com sucesso."
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao alterar dados!"
      });
    },

    async obterPorId(id) {
      await api.get(`/${id}`).then((resposta) => {
        this.empresa = resposta.data
      }).catch(() => {
        this.snackbar = true;
        this.severidade = "red";
        this.mensagem = "Erro ao obter dados pelo ID!"
      });
    },

    cancelar () {
      this.$router.push('/empresas')
    },

    persistir() {
      if (this.$refs.form.validate()) {
        if (this.empresa.id) {
          this.editarItem();
        } else {
          this.salvarItem();
        }
      }
    },

    empresaVazia() {
      return {
        id: null,
        cnpj: null,
        razaoSocial: null,
        logradouro: null,
        numero: null,
        complemento: null,
        bairro: null,
        municipio: null,
        cep: null,
        telefone: null,
        email: null,
        site: null,
        usuario: null,
        senha: null,
        rSenha: null
      }
    },

  },

  mounted () {
    const id = this.$route.params.id
    if (id) {
      this.obterPorId(id)
    }
  },

  computed: {
    titulo() {
      return this.empresa.id ? 'Editar Empresa' : 'Nova Empresa'
    },

    validarSenhasIguais() {
      return [(v) => (v === this.empresa.senha) || 'Senhas diferentes!']
    }

  },

}

</script>
