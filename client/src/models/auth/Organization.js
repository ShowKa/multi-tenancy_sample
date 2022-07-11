import { useApi } from '@/plugins/api'
import constants from './const'

const Api = useApi()
const auth = constants.context

export default {
  async getAll() {
    const url = `${auth}/organizations`
    const res = await Api.get(url)
    return res.data
  },
  async getMine() {
    const url = `${auth}/organizations/mine`
    const res = await Api.get(url)
    return res.data
  },
  async post(organization) {
    const url = `${auth}/organizations`
    const res = await Api.post(url, organization)
    return res.data
  },
  async patch(organization) {
    const url = `${auth}/organizations`
    const res = await Api.patch(url, organization)
    return res.data
  }
}
