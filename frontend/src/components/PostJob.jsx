import React, { useState } from 'react'
import api from './api'

export default function PostJob(){
  const [form, setForm] = useState({ title:'SDE Intern', description:'Work on features', location:'Remote', company:'Zidio', internship:true })
  const submit = async (e) => {
    e.preventDefault()
    const {data} = await api.post('/jobs', form)
    alert('Posted: '+data.title)
  }
  return (
    <form onSubmit={submit} style={{maxWidth:500}}>
      <h3>Post a Job/Internship</h3>
      <label>Title</label><input value={form.title} onChange={e=>setForm({...form, title:e.target.value})}/><br/>
      <label>Description</label><textarea value={form.description} onChange={e=>setForm({...form, description:e.target.value})}/><br/>
      <label>Location</label><input value={form.location} onChange={e=>setForm({...form, location:e.target.value})}/><br/>
      <label>Company</label><input value={form.company} onChange={e=>setForm({...form, company:e.target.value})}/><br/>
      <label>Internship</label><input type="checkbox" checked={form.internship} onChange={e=>setForm({...form, internship:e.target.checked})}/><br/>
      <button type="submit">Post</button>
    </form>
  )
}
