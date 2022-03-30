import MockAdapter from 'axios-mock-adapter'

const organization01 = {
  id: 'org_ZsZwfWxN6ZT02w2h',
  identifiedName: 'org-1',
  displayName: '1st MockOrg',
}
const organization02 = {
  id: 'org_QpiDCUasY7EDE3zB',
  identifiedName: 'org-2',
  displayName: '2nd MockOrg',
}

export default {
  run: client => {
    const mock = new MockAdapter(client)
    mock.onGet('/organizations').reply(200, [organization01, organization02])
    mock.onGet('/organizations/mine').reply(200, organization01)
    mock.onPost('/organizations').reply(200, organization01)
    mock.onPatch('/organizations').reply(200, organization01)
  }
}
