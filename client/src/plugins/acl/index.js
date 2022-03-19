const can = (roles, acl) => {
  return (verb, subject) => {
    const target = `${verb}:${subject}`
    for (var i = 0; i < roles.value.length; i++) {
      const r = roles.value[i]
      const permisions = acl.value[r]
      if (permisions && permisions.includes(target)) {
        return true
      }
    }
    return false
  }
}

export default {
  install: (app, { roles, acl }) => {
    app.config.globalProperties.$can = can(roles, acl)
    app.provide('$can', can(roles, acl))
  }
}
