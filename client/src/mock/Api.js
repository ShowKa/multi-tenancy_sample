import MockAdapter from 'axios-mock-adapter'

const organization01 = {
  id: '1',
  identifiedName: 'org1',
  displayName: '1stOrganization',
}
const organization02 = {
  id: '2',
  identifiedName: 'org2',
  displayName: '2ndOrganization',
}

export default {
  run: client => {
    const mock = new MockAdapter(client)
    mock.onGet('/organizations').reply(200, [organization01, organization02])
  }
}
