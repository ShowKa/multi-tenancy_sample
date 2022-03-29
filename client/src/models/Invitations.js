import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async post(invitation) {
    const url = `invitations`
    const res = await Api.post(url, invitation)
    return res.data
  }
}
