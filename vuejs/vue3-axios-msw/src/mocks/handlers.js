import { http, HttpResponse } from 'msw'
import { authService } from './auth'

let fakeDB = [
  { Make_ID: 1, Make_Name: 'Toyota' },
  { Make_ID: 2, Make_Name: 'Honda' }
]

export const handlers = [
  http.get('https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes', () => {
    return HttpResponse.json({
      Count: fakeDB.length,
      Message: 'OK - mocked',
      Results: fakeDB
    }, { status: 200 })
  }),

  http.post('/api/makes', async ({ request }) => {
    const body = await request.json()
    const newId = Math.max(...fakeDB.map(x=>x.Make_ID), 0) + 1
    const newItem = { Make_ID: newId, Make_Name: body.Make_Name || 'Unknown' }
    fakeDB.push(newItem)
    return HttpResponse.json(newItem, { status: 201 })
  }),

  http.put('/api/makes/:id', async ({ request, params }) => {
    const id = Number(params.id)
    const body = await request.json()
    const idx = fakeDB.findIndex(x=>x.Make_ID === id)
    if (idx === -1) return new HttpResponse(null, { status: 404 })
    fakeDB[idx] = { ...fakeDB[idx], ...body }
    return HttpResponse.json(fakeDB[idx], { status: 200 })
  }),

  http.delete('/api/makes/:id', ({ params }) => {
    const id = Number(params.id)
    fakeDB = fakeDB.filter(x=>x.Make_ID !== id)
    return new HttpResponse(null, { status: 204 })
  }),

  http.head('/api/makes', () => {
    return new HttpResponse(null, { status: 200 })
  }),

  http.post('/api/auth/login', async ({ request }) => {
    const body = await request.json()
    const response = await authService.login(body)
    
    if (response.success) {
      return HttpResponse.json(response, { status: 200 })
    } else {
      return HttpResponse.json(response, { status: 401 })
    }
  }),

  http.post('/api/auth/logout', async ({ request }) => {
    const body = await request.json()
    const response = await authService.logout(body.token)
    
    return HttpResponse.json(response, { status: 200 })
  }),

  http.get('/api/auth/validate', async ({ request }) => {
    const url = new URL(request.url)
    const token = url.searchParams.get('token')
    
    if (!token) {
      return HttpResponse.json({
        success: false,
        message: 'Token required'
      }, { status: 400 })
    }
    
    const response = await authService.validateToken(token)
    
    if (response.success) {
      return HttpResponse.json(response, { status: 200 })
    } else {
      return HttpResponse.json(response, { status: 401 })
    }
  }),

  http.get('/api/auth/users', async ({ request }) => {
    const url = new URL(request.url)
    const token = url.searchParams.get('token')
    
    if (!token) {
      return HttpResponse.json({
        success: false,
        message: 'Token required'
      }, { status: 400 })
    }
    
    const response = await authService.getAllUsers(token)
    
    if (response.success) {
      return HttpResponse.json(response, { status: 200 })
    } else {
      return HttpResponse.json(response, { status: 403 })
    }
  }),

  http.post('/api/auth/users', async ({ request }) => {
    const url = new URL(request.url)
    const token = url.searchParams.get('token')
    const body = await request.json()
    
    if (!token) {
      return HttpResponse.json({
        success: false,
        message: 'Token required'
      }, { status: 400 })
    }
    
    const response = await authService.createUser(token, body)
    
    if (response.success) {
      return HttpResponse.json(response, { status: 201 })
    } else {
      return HttpResponse.json(response, { status: 403 })
    }
  })
]
