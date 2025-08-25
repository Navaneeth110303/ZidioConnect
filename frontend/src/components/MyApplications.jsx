import React, { useEffect, useState } from 'react'
import api from './api'

export default function MyApplications(){
  const [apps, setApps] = useState([])
  useEffect(()=>{ api.get('/applications/me').then(r=>setApps(r.data)) },[])
  return (
    <div>
      <h3>My Applications</h3>
      {apps.map(a => (
        <div key={a.id} style={{border:'1px solid #ddd', padding:10, marginBottom:10}}>
          <div>Application #{a.id} — Status: <b>{a.status}</b></div>
          <div>Job: {a.job?.title} at {a.job?.company}</div>
        </div>
      ))}
    </div>
  )
}
