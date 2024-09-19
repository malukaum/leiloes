<template>
  <v-navigation-drawer>
    <v-list-item router :to="'/'">Gerenciador de Leilões</v-list-item>
    <v-divider></v-divider>
    <v-list-item router :to="'/unidades'">
      <v-list-item-icon>
        <v-icon>mdi-pencil-ruler</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title>Unidades</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-list-item router :to="'/empresas'">
      <v-list-item-icon>
        <v-icon>mdi-office-building</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title>Empresas</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-list-item  router :to="'/leiloes'">
      <v-list-item-icon>
        <v-icon>mdi-gavel</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title>Leilões</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </v-navigation-drawer>
</template>

<script>
    export default {
        name: 'Navbar'
    }
</script>
