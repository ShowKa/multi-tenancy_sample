import { useApi } from '@/plugins/api'
import constants from './const'

const Api = useApi()
const auth = constants.context

export default {
  async getAll() {
    const url = `${auth}/members`
    const res = await Api.get(url)
    return res.data
  }
}
