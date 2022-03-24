import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async getAll() {
    const url = `organizations`
    const res = await Api.get(url)
    return res.data
  },
  async post(organization) {
    const url = `organizations`
    const res = await Api.post(url, organization)
    return res.data
  }
}
