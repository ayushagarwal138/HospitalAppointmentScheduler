import React, { useEffect, useMemo, useState } from 'react';
import './App.css';
import { addDoctor, bookAppointment, fetchDoctors } from './api';
import AddDoctorForm from './components/AddDoctorForm';
import DoctorList from './components/DoctorList';
import AppointmentBooking from './components/AppointmentBooking';
import OutputPanel from './components/OutputPanel';

function App() {
  const [doctors, setDoctors] = useState([]);
  const [outputMessage, setOutputMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const loadDoctors = async () => {
    try {
      const data = await fetchDoctors();
      setDoctors(data);
    } catch (err) {
      setErrorMessage(err.message || 'Failed to load doctors.');
    }
  };

  useEffect(() => {
    loadDoctors();
  }, []);

  const handleDoctorAdded = async (doctorPayload) => {
    await addDoctor(doctorPayload);
    await loadDoctors();
  };

  const handleBookAppointment = async (bookingPayload) => {
    const result = await bookAppointment(bookingPayload);
    await loadDoctors();
    return result;
  };

  const specializations = useMemo(() => {
    const set = new Set(doctors.map((d) => d.specialization));
    return Array.from(set);
  }, [doctors]);

  return (
    <div className="app-container">
      <header>
        <h1>Hospital Appointment Scheduler</h1>
      </header>
      <main>
        <section className="left-column">
          <AddDoctorForm
            onDoctorAdded={handleDoctorAdded}
            setOutputMessage={setOutputMessage}
            setErrorMessage={setErrorMessage}
          />
          <AppointmentBooking
            specializations={specializations}
            onBookAppointment={handleBookAppointment}
            setOutputMessage={setOutputMessage}
            setErrorMessage={setErrorMessage}
          />
        </section>
        <section className="right-column">
          <DoctorList doctors={doctors} />
          <OutputPanel
            outputMessage={outputMessage}
            errorMessage={errorMessage}
          />
        </section>
      </main>
    </div>
  );
}

export default App;

