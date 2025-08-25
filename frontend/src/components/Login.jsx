import React, { useState } from 'react'
import api from './api'

export default function Login({ onLogin }) {
  const [email, setEmail] = useState('student@zidio.in')
  const [password, setPassword] = useState('pass123')
  const [role, setRole] = useState('STUDENT')
  const [tab, setTab] = useState('login')

  const submit = async (e) => {
    e.preventDefault()
    if (tab==='login'){
      const {data} = await api.post('/auth/login', { email, password })
      localStorage.setItem('token', data.token)
      localStorage.setItem('role', data.role)
      onLogin(data.token, data.role)
    } else {
      await api.post('/auth/register', { email, password, fullName: 'User', role })
      alert('Registered! Now switch to Login.')
    }
  }

  return (
    <div style={{maxWidth:420}}>
      <div style={{display:'flex', gap:8}}>
        <button onClick={()=>setTab('login')} disabled={tab==='login'}>Login</button>
        <button onClick={()=>setTab('register')} disabled={tab==='register'}>Register</button>
      </div>
      <form onSubmit={submit}>
        <div><label>Email</label><br/><input value={email} onChange={e=>setEmail(e.target.value)} /></div>
        <div><label>Password</label><br/><input type="password" value={password} onChange={e=>setPassword(e.target.value)} /></div>
        {tab==='register' && (
          <div>
            <label>Role</label><br/>
            <select value={role} onChange={e=>setRole(e.target.value)}>
              <option>STUDENT</option>
              <option>RECRUITER</option>
            </select>
          </div>
        )}
        <br/>
        <button type="submit">{tab==='login'?'Login':'Register'}</button>
      </form>
    </div>
  )
}
