
const plugin = {
  get: () => { },
  set: () => { },
  remove: () => { }
}

export default {
  install: (app, option = {}) => {
    for (const [key, value] of Object.entries(option)) {
      plugin[key] = value
    }
    app.config.globalProperties.$currentOrganization = plugin
    app.provide('$currentOrganization', plugin)
  }
}
