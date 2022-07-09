import { useApi } from '@/plugins/api'
import constants from './const'

const Api = useApi()
const auth = constants.context

export default {
  async get() {
    const url = `${auth}/permissions`
    const res = await Api.get(url)
    return res.data
  }
}
