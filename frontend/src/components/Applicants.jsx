import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import api from './api'

export default function Applicants(){
  const { jobId } = useParams()
  const [apps, setApps] = useState([])
  useEffect(()=>{ api.get('/applications/job/'+jobId).then(r=>setApps(r.data)) },[jobId])

  const setStatus = async (id, status) => {
    const {data} = await api.post(`/applications/${id}/status/${status}`)
    setApps(apps.map(a => a.id===id ? data : a))
  }

  return (
    <div>
      <h3>Applicants for Job #{jobId}</h3>
      {apps.map(a => (
        <div key={a.id} style={{border:'1px solid #ddd', padding:10, marginBottom:10}}>
          <div>Student: {a.student?.fullName} ({a.student?.email})</div>
          <div>Status: <b>{a.status}</b></div>
          <div>
            <button onClick={()=>setStatus(a.id,'SHORTLISTED')}>Shortlist</button>
            <button onClick={()=>setStatus(a.id,'REJECTED')}>Reject</button>
          </div>
        </div>
      ))}
    </div>
  )
}
