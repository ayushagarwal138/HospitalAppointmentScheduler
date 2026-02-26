## 🏥 Hospital Appointment Scheduler

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![React](https://img.shields.io/badge/React-18-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Vercel](https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=vercel&logoColor=white)
![Render](https://img.shields.io/badge/Render-3333FF?style=for-the-badge&logo=render&logoColor=white)

> A production-ready hospital appointment system that allocates doctors **fairly** using a priority-based algorithm and enforces **daily capacity limits** per doctor.

---

### 📖 Overview

**Hospital Appointment Scheduler** is a full‑stack application built to mirror a real hospital scheduling scenario.  
It focuses on **data modeling**, **prioritization logic**, and **end‑to‑end deployment**:

- Doctors have a **specialization**, **maximum daily capacity**, and **live appointment count**.
- Patients book appointments **by specialization** rather than by a specific doctor.
- The system automatically selects the **least busy available doctor** and prevents over‑booking.

The project is fully deployable, with:

- **Frontend** hosted on **Vercel**
- **Backend API** hosted on **Render**
- **PostgreSQL** as the persistent data store

---

### 🌐 Live Demo

- **Frontend (Vercel)**: `https://your-frontend-url.vercel.app`  
- **Backend (Render)**: `https://your-backend-url.onrender.com`

---

### 🎥 Demo Video

A short walkthrough video explaining the architecture, core flows, and deployment:

- **Video Link**: `https://your-video-link.com`

The video covers:

- Problem statement and constraints from the assignment PDF.
- Data model design and why the fields were chosen.
- Booking algorithm (fewest current appointments + capacity check).
- Live demo of add doctor, list doctors, and booking flows.
- Quick tour of the code structure (backend + frontend) and deployment setup.

---

### 🧱 Architecture & Tech Stack

- **Frontend**: React 18 + Vite, modern responsive CSS
- **Backend**: Spring Boot 3 (Java 17), layered architecture (Controller → Service → Repository)
- **Database**: PostgreSQL with JPA/Hibernate
- **Validation**: Jakarta Bean Validation (input validation on DTOs and entities)
- **Build & Dependency Management**: Maven
- **Deployment**:
  - Vercel for the React SPA
  - Render for the Spring Boot API

High‑level flow:

1. User interacts with the React UI to add doctors or book appointments.
2. Frontend calls the Spring Boot REST API.
3. Backend persists and reads doctors from PostgreSQL.
4. Booking logic runs in the service layer, ensuring fairness and capacity constraints.
5. Responses and errors are returned as clean JSON and rendered in the UI output panel.

---

### 📌 Problem Statement (From Assignment)

Build a hospital appointment scheduler that:

- **Add Doctor**: User must be able to add doctor details.
- **View All Doctors**: User must be able to view the doctor list.
- **BookAppointment(specialization)**:
  - Allocate a doctor with the **fewest current appointments**.
  - **Reject** the booking if all doctors for that specialization are full.
- Provide the following UI:
  - Add Doctor form
  - Doctor listing screen
  - Appointment booking screen
  - Output display panel

All of the above are implemented in this project.

---

### 🧬 Data Model

The core domain entity is `Doctor`:

- **doctorId**: unique identifier (auto‑generated primary key).
- **specialization**: doctor’s specialization (e.g. Cardiologist, Dermatologist).
- **maxDailyPatients**: maximum number of patients the doctor can see per day.
- **currentAppointments**: number of appointments already booked for the current day.

Validation rules:

- `specialization` is required and cannot be blank.
- `maxDailyPatients` and `currentAppointments` must be non‑negative integers.

---

### ✅ Feature Breakdown

- **Add Doctor**
  - Clean form with validation for required fields and non‑negative capacity.
  - On success, the form resets and the doctors list refreshes.

- **View All Doctors**
  - Tabular view with:
    - `doctorId`
    - `specialization`
    - `maxDailyPatients`
    - `currentAppointments`
  - Always reflects the latest state after any add or booking action.

- **Book Appointment**
  - User selects a specialization from existing doctors (or types one when no doctors exist yet).
  - Backend fetches all doctors with that specialization.
  - Filters to only doctors where `currentAppointments < maxDailyPatients`.
  - Chooses the doctor with:
    - The **fewest `currentAppointments`**, and
    - As a tie‑breaker, the **smallest `doctorId`** for deterministic behavior.
  - Increments the selected doctor’s `currentAppointments`.
  - Returns a concise summary of the allocation, including capacity usage.

- **Rejection When Full**
  - If no doctor has remaining capacity for that specialization, the backend throws a specific exception.
  - The global exception handler converts this into a clear error message which the frontend displays.

- **UI / UX**
  - Four distinct panels:
    - Add Doctor form
    - Doctor listing table
    - Appointment booking form
    - Output panel showing success or error messages
  - Messages are reset between actions to avoid confusion.

---

### 🔌 API Design

**Base URL (local)**: `http://localhost:8080/api`  
**Base URL (Render)**: `https://your-backend-url.onrender.com/api`

- **POST** `/api/doctors` – Create a new doctor

  Request:

  ```json
  {
    "specialization": "Cardiologist",
    "maxDailyPatients": 10
  }
  ```

  Response (201 Created):

  ```json
  {
    "doctorId": 1,
    "specialization": "Cardiologist",
    "maxDailyPatients": 10,
    "currentAppointments": 0
  }
  ```

- **GET** `/api/doctors` – List all doctors

  Response:

  ```json
  [
    {
      "doctorId": 1,
      "specialization": "Cardiologist",
      "maxDailyPatients": 10,
      "currentAppointments": 3
    }
  ]
  ```

- **POST** `/api/appointments/book` – Book appointment by specialization

  Request:

  ```json
  {
    "specialization": "Cardiologist"
  }
  ```

  Success response:

  ```json
  {
    "message": "Appointment booked successfully.",
    "doctorId": 1,
    "specialization": "Cardiologist",
    "currentAppointments": 4,
    "maxDailyPatients": 10
  }
  ```

  Error responses:

  - 404 – No doctors found for the given specialization.
  - 409 – All doctors for that specialization are fully booked.
  - 400 – Validation errors on input.

---

### 🧪 Error Handling & Validation

- **Global exception handler** converts backend exceptions into consistent JSON responses.
- **Validation**:
  - Request DTOs are annotated with constraints (`@NotBlank`, etc.).
  - Validation errors include field‑level messages in the response.
- **Frontend**:
  - Displays both success and error messages in a dedicated `Output` panel.
  - Basic client‑side validation (required fields, non‑negative numbers).

---

### 🖥️ Running the Project Locally

#### Prerequisites

- Java 17
- Maven
- Node.js (LTS) + npm
- PostgreSQL running locally

#### Backend (Spring Boot)

1. Create a PostgreSQL database, e.g. `hospital_scheduler`.
2. Update `backend/src/main/resources/application.properties` with your DB credentials.
3. From the `backend` folder:

   ```bash
   mvn spring-boot:run
   ```

4. The backend will start on `http://localhost:8080`.

#### Frontend (React + Vite)

1. From the `frontend` folder:

   ```bash
   npm install
   npm run dev
   ```

2. The frontend will start on the Vite dev server (by default `http://localhost:5173`).
3. Confirm that `frontend/src/api.js` points to the correct backend base URL (local or Render).

---

### ☁️ Deployment Notes

- **Frontend (Vercel)**
  - Build command: `npm run build`
  - Output directory: `dist`
  - (Optional) Configure `VITE_API_BASE_URL` to point to the Render backend.

- **Backend (Render)**
  - Create a Web Service from the GitHub repo.
  - Build command: `mvn clean package`
  - Start command:

    ```bash
    java -jar target/hospital-appointment-scheduler-0.0.1-SNAPSHOT.jar
    ```

  - Configure environment variables for PostgreSQL or attach a managed PostgreSQL instance.

---

### 📁 Repository Structure

- `backend/` – Spring Boot application (controllers, services, repositories, DTOs, entities, exception handling)
- `frontend/` – React application (components, styling, API client)

The structure keeps API logic and UI concerns clearly separated, making it easy for reviewers and future contributors to navigate.

---

### 👨‍💻 Author

- **Ayush Agarwal – Java Full Stack Developer**
  - [LinkedIn](https://www.linkedin.com/in/ayush-agarwal-50668927b/)
  - [Portfolio](https://portfolio-alpha-puce-1o6qxo19x8.vercel.app/)
