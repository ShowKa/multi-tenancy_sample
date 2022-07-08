import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async get() {
    const url = `auth/permissions`
    const res = await Api.get(url)
    return res.data
  }
}
