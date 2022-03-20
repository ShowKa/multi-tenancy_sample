const storage = localStorage

const plugin = {
  get(name) {
    return storage.getItem(name)
  },
  set(name, value) {
    storage.setItem(name, value)
  },
  remove(name) {
    storage.removeItem(name)
  },
  exists(name) {
    return storage.getItem(name) != null
  },
}

export default {
  install: (app, extension) => {
    for (const [key, value] of Object.entries(extension)) {
      if (typeof value == 'function') {
        const func = value
        plugin[key] = (item) => {
          return item ? func(plugin, item) : func(plugin)
        }
      } else if (key == 'keys') {
        const arrayValue = typeof value == 'string' ? [value] : value
        for (const targetKeyName of arrayValue) {
          plugin['get' + targetKeyName] = () => {
            return plugin.get(targetKeyName)
          }
          plugin['set' + targetKeyName] = (item) => {
            plugin.set(targetKeyName, item)
          }
          plugin['remove' + targetKeyName] = () => {
            plugin.remove(targetKeyName)
          }
          plugin['exists' + targetKeyName] = () => {
            return plugin.exists(targetKeyName)
          }
        }
      }
    }
    app.config.globalProperties.$storage = plugin
    app.provide('$storage', plugin)
  }
}
