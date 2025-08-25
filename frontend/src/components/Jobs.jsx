import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import api from './api'

export default function Jobs(){
  const [jobs, setJobs] = useState([])
  const role = localStorage.getItem('role')

  useEffect(()=>{
    api.get('/jobs').then(res=>setJobs(res.data))
  }, [])

  const apply = async (id) => {
    await api.post(`/applications/${id}`)
    alert('Applied!')
  }

  return (
    <div>
      <h3>Jobs & Internships</h3>
      {jobs.map(j => (
        <div key={j.id} style={{border:'1px solid #ddd', padding:10, marginBottom:10}}>
          <b>{j.title}</b> &mdash; {j.company} ({j.location}) {j.internship? 'Internship':'Job'}
          <p>{j.description}</p>
          {role==='STUDENT' && <button onClick={()=>apply(j.id)}>Apply</button>}
          {role==='RECRUITER' && <Link to={`/applicants/${j.id}`}>View Applicants</Link>}
        </div>
      ))}
    </div>
  )
}
