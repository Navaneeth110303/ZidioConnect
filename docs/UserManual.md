# User Manual

## Roles
- **Student**: Register/Login, view jobs, apply, track application status.
- **Recruiter**: Login, post jobs, view applicants, shortlist/reject.
- **Admin**: (extend later) moderate users/jobs.

## Steps
1. Login (use seeded accounts or register).
2. Students -> "Apply" on a job.
3. Recruiters -> "Post Job", then "View Applicants" to manage statuses.

## Notes
- JWT auth stored in browser localStorage.
- API base URL is `http://localhost:8080/api` (change in `frontend/src/components/api.js` if needed).
