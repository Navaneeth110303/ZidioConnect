CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  full_name VARCHAR(255),
  role VARCHAR(32) NOT NULL,
  resume_url VARCHAR(255),
  enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS job (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  description TEXT,
  location VARCHAR(255),
  internship BOOLEAN,
  company VARCHAR(255),
  created_at DATETIME,
  recruiter_id BIGINT,
  CONSTRAINT fk_job_recruiter FOREIGN KEY (recruiter_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS applications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  student_id BIGINT NOT NULL,
  job_id BIGINT NOT NULL,
  status VARCHAR(32),
  applied_at DATETIME,
  CONSTRAINT fk_app_student FOREIGN KEY (student_id) REFERENCES users(id),
  CONSTRAINT fk_app_job FOREIGN KEY (job_id) REFERENCES job(id)
);
