import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async getAll() {
    const url = `members`
    const res = await Api.get(url)
    return res.data
  }
}
