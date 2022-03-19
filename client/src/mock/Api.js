import MockAdapter from 'axios-mock-adapter'

export default {
  run: client => {
    new MockAdapter(client)
  }
}
