module.exports = {
  devServer: {
    proxy: {
      '/unidades': {
        target: 'http://localhost:8081'
      },
      '/leiloes': {
        target: 'http://localhost:8081'
      },
      '/empresas': {
        target: 'http://localhost:8081'
      }
    }
  }
}
