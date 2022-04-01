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

const member01 = {
  id: '1',
  name: 'Member 1st',
  mailAddress: 'member1@org.com',
}

const member02 = {
  id: '2',
  name: 'Member 2nd',
  mailAddress: 'member2@org.com',
}

export default {
  run: client => {
    const mock = new MockAdapter(client)
    mock.onGet('/organizations').reply(200, [organization01, organization02])
    mock.onGet('/organizations/mine').reply(200, organization01)
    mock.onPost('/organizations').reply(200, organization01)
    mock.onPatch('/organizations').reply(200, organization01)
    mock.onGet('/members').reply(200, [member01, member02])
  }
}
