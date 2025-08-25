import React, { useState } from 'react'
import { Routes, Route, Link, Navigate, useNavigate } from 'react-router-dom'
import Login from './components/Login'
import Jobs from './components/Jobs'
import PostJob from './components/PostJob'
import MyApplications from './components/MyApplications'
import Applicants from './components/Applicants'

export default function App(){
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [role, setRole] = useState(localStorage.getItem('role'));

  const logout = () => {
    localStorage.clear(); setToken(null); setRole(null);
  }

  return (
    <div style={{fontFamily:'system-ui', padding:20}}>
      <header style={{display:'flex', gap:12, alignItems:'center'}}>
        <h2 style={{marginRight:'auto'}}>Zidio Connect</h2>
        {token ? (<>
          <span>{role}</span>
          <button onClick={logout}>Logout</button>
        </>) : <></>}
        <Link to="/">Home</Link>
        {role==='RECRUITER' && <Link to="/post">Post Job</Link>}
        {role==='STUDENT' && <Link to="/applications">My Applications</Link>}
      </header>
      <hr/>
      <Routes>
        <Route path="/" element={token ? <Jobs/> : <Login onLogin={(t,r)=>{setToken(t);setRole(r)}}/>} />
        <Route path="/post" element={role==='RECRUITER' ? <PostJob/> : <Navigate to="/" />} />
        <Route path="/applications" element={role==='STUDENT' ? <MyApplications/> : <Navigate to="/" />} />
        <Route path="/applicants/:jobId" element={role==='RECRUITER' ? <Applicants/> : <Navigate to="/" />} />
      </Routes>
    </div>
  )
}
