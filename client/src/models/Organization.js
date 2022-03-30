import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async getAll() {
    const url = `organizations`
    const res = await Api.get(url)
    return res.data
  },
  async get(id) {
    const url = `organizations/${id}`
    const res = await Api.get(url)
    return res.data
  },
  async post(organization) {
    const url = `organizations`
    const res = await Api.post(url, organization)
    return res.data
  },
  async patch(organization) {
    const url = `organizations`
    const res = await Api.patch(url, organization)
    return res.data
  }
}
