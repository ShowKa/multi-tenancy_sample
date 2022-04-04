import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async get() {
    const url = `permissions`
    const res = await Api.get(url)
    return res.data
  }
}
