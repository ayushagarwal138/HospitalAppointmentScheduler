import React, { useState } from 'react';

const AppointmentBooking = ({
  specializations,
  onBookAppointment,
  setOutputMessage,
  setErrorMessage,
}) => {
  const [specialization, setSpecialization] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setOutputMessage('');
    setErrorMessage('');

    if (!specialization.trim()) {
      setErrorMessage('Please select or enter a specialization.');
      return;
    }

    try {
      const result = await onBookAppointment({ specialization: specialization.trim() });
      const msg = `${result.message} Allocated Doctor ID: ${result.doctorId} (${result.specialization}). ` +
        `Current appointments: ${result.currentAppointments}/${result.maxDailyPatients}.`;
      setOutputMessage(msg);
    } catch (err) {
      setErrorMessage(err.message || 'Failed to book appointment.');
    }
  };

  return (
    <div className="card">
      <h2>Book Appointment</h2>
      <form onSubmit={handleSubmit} className="form">
        <div className="form-row">
          <label>Specialization</label>
          {specializations.length > 0 ? (
            <select
              value={specialization}
              onChange={(e) => setSpecialization(e.target.value)}
            >
              <option value="">Select specialization</option>
              {specializations.map((spec) => (
                <option key={spec} value={spec}>
                  {spec}
                </option>
              ))}
            </select>
          ) : (
            <input
              type="text"
              value={specialization}
              onChange={(e) => setSpecialization(e.target.value)}
              placeholder="e.g. Cardiologist"
            />
          )}
        </div>
        <button type="submit">Book</button>
      </form>
    </div>
  );
};

export default AppointmentBooking;

