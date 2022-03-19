import { mount } from '@vue/test-utils'
import Message from '@/components/message/Message.vue'
import { showSuccess } from '@/components/message/useMessage'
import { nextTick } from 'vue'

describe('Message.vue', () => {
  it('renders props.msg when passed', async () => {
    const msg = 'new message'
    const wrapper = mount(Message)
    showSuccess(msg)
    await nextTick()
    expect(wrapper.text()).toMatch(msg)
  })
})
