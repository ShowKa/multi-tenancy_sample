import axios from 'axios'
import mock from '@/mock/Api'
import { app } from '@/main'
import { showWarning, showError } from '@/components/message/useMessage'

const SERVER_URL = process.env.VUE_APP_API_ENDPOINT

const instance = axios.create({
  baseURL: SERVER_URL,
  timeout: 30000
})

// error handling
instance.interceptors.response.use(
  response => response,
  error => {
    if (error.response) {
      const status = error.response.status
      var message = null
      switch (status) {
        case 409:
        case 422:
          message = error.response.data.message
          break
        case 401:
          message = '認証情報に誤りがあります。ログインし直してください。'
          break
        case 403:
          message = 'ユーザーに実行権限がありません。'
          break
        case 500:
        default:
          message = '想定外のエラーが発生しました。このエラーが何度も発生する場合、管理者に連絡してください。'
      }
      showError(message)
      throw new Error(message)
    } else {
      showError('想定外のエラーが発生しました。ネットワーク接続を確認してから、再度実施してください。')
      throw error
    }
  }
)

// mock
if (process.env.VUE_APP_USE_MOCK && JSON.parse(process.env.VUE_APP_USE_MOCK)) {
  mock.run(instance)
}

async function execute(method, resource, data) {
  const $auth = app.config.globalProperties.$auth
  let token = await $auth.getTokenSilently()
  return instance({
    method: method,
    url: resource,
    headers: {
      Authorization: `Bearer ${token}`
    },
    data, // data = request body
    config: {
      transformResponse: [
        function (data) {
          return data && (typeof data === 'string') ? JSON.parse(data) : data
        }
      ]
    }
  })
}

function get(resource) {
  return execute(
    'GET',
    resource,
    {}
  )
}

async function post(resource, data) {
  var ret = null
  try {
    start()
    ret = await execute('POST', resource, data)
  } finally {
    end()
  }
  return ret
}

async function patch(resource, data) {
  var ret = null
  try {
    start()
    ret = await execute('PATCH', resource, data)
  } finally {
    end()
  }
  return ret
}

async function put(resource, data) {
  var ret = null
  try {
    start()
    ret = await execute('PUT', resource, data)
  } finally {
    end()
  }
  return ret
}

async function del(resource, data) {
  var ret = null
  try {
    start()
    ret = await execute('DELETE', resource, data)
  } finally {
    end()
  }
  return ret
}

async function search(resource, params) {
  params = params || {}
  var paramsString = ""
  for (const [key, value] of Object.entries(params)) {
    if (value) {
      paramsString += `&${key}=${value}`
    }
  }
  if (paramsString) {
    paramsString = paramsString.replaceAll(/^&/g, "?")
  }
  const res = await execute(
    'GET',
    resource + paramsString,
    {}
  )
  if (res.data.overLimit) {
    showWarning(`規定の表示可能件数（${res.data.limit}件）を超過しました。条件を追加し、再度検索してください。`)
  }
  return {
    data: res.data.list
  }
}

async function download(resource, { fileName, charsetName }, params,) {
  charsetName = charsetName || "UTF-8"
  params = params || {}
  const $auth = app.config.globalProperties.$auth
  let token = await $auth.getTokenSilently()
  var paramsString = ""
  for (const [key, value] of Object.entries(params)) {
    paramsString += `&${key}=${value}`
  }
  instance({
    method: 'GET',
    url: `${resource}?charsetName=${charsetName}${paramsString}`,
    responseType: 'blob',
    headers: {
      Authorization: `Bearer ${token}`
    }
  }).then(response => {
    const url = URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', fileName);
    document.body.appendChild(link);
    link.click();
    URL.revokeObjectURL(url);
  })
}

async function upload(resource, { file, charsetName, method }, params,) {
  charsetName = charsetName || "UTF-8"
  method = method || "Post"
  params = params || {}
  const $auth = app.config.globalProperties.$auth
  let token = await $auth.getTokenSilently()
  const formData = new FormData();
  formData.append('multipartFile', file)
  formData.append('charsetName', charsetName)
  for (const [key, value] of Object.entries(params)) {
    formData.append(key, value)
  }
  return instance({
    method: method,
    url: resource,
    data: formData,
    headers: {
      Authorization: `Bearer ${token}`,
      'content-type': 'multipart/form-data'
    }
  })
}

var start = () => { }
var end = () => { }

const plugin = {
  get, post, put, patch, del, search, download, upload
}

export default {
  install: (app, option = {}) => {
    // option
    if (option.startSubmit) {
      start = option.startSubmit
    }
    if (option.endSubmit) {
      end = option.endSubmit
    }
    // setup
    app.config.globalProperties.$api = plugin
    app.provide('$api', plugin)
  }
}

export function useApi() {
  return plugin
}
