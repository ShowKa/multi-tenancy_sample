import { useApi } from '@/plugins/api'
const Api = useApi()

export default {
  async post(invitation) {
    const url = `auth/invitations`
    const res = await Api.post(url, invitation)
    return res.data
  }
}
