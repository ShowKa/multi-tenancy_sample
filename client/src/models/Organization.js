import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async getAll() {
    const url = `organizations`
    const res = await Api.get(url)
    return res.data
  }
}
