<template>
  <v-data-table :headers="cabecalho" :items="itens" :footer-props="configRodape" :no-data-text="'Nenhum Registro Encontrado'">
    <template v-slot:item.data="{ item }">
      {{ formatarData(item.inicioPrevisto) }}
    </template>
    <template v-slot:item.total="{ item }">
      {{ item.total | toCurrency }}
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon size="small" @click="$emit('deletarItem', item)">
        mdi-delete
      </v-icon>
      <v-icon size="small" @click="$emit('editar', item)">
        mdi-pencil
      </v-icon>
    </template>
  </v-data-table>
</template>
<script>
export default {
  name: 'Tabela',
  props: ['cabecalho', 'itens'],
  data: () => ({
    configRodape: {
      'items-per-page-text': 'Itens por página'
    },
  }),

  methods: {
    formatarData(data) {
      return new Date(data).toLocaleString()
    }
  }
}
</script>
