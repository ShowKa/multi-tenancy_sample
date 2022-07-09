import { useApi } from '@/plugins/api'
import constants from './const'

const Api = useApi()
const auth = constants.context

export default {
  async post(invitation) {
    const url = `${auth}/invitations`
    const res = await Api.post(url, invitation)
    return res.data
  }
}
