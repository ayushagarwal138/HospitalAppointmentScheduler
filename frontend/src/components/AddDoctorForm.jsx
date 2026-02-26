import React, { useState } from 'react';

const AddDoctorForm = ({ onDoctorAdded, setOutputMessage, setErrorMessage }) => {
  const [specialization, setSpecialization] = useState('');
  const [maxDailyPatients, setMaxDailyPatients] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setOutputMessage('');
    setErrorMessage('');

    const parsedMax = parseInt(maxDailyPatients, 10);
    if (!specialization.trim() || Number.isNaN(parsedMax) || parsedMax < 0) {
      setErrorMessage('Please provide a valid specialization and non-negative max daily patients.');
      return;
    }

    try {
      await onDoctorAdded({
        specialization: specialization.trim(),
        maxDailyPatients: parsedMax,
      });
      setSpecialization('');
      setMaxDailyPatients('');
      setOutputMessage('Doctor added successfully.');
    } catch (err) {
      setErrorMessage(err.message || 'Failed to add doctor.');
    }
  };

  return (
    <div className="card">
      <h2>Add Doctor</h2>
      <form onSubmit={handleSubmit} className="form">
        <div className="form-row">
          <label>Specialization</label>
          <input
            type="text"
            value={specialization}
            onChange={(e) => setSpecialization(e.target.value)}
            placeholder="e.g. Cardiologist"
          />
        </div>
        <div className="form-row">
          <label>Max Daily Patients</label>
          <input
            type="number"
            min="0"
            value={maxDailyPatients}
            onChange={(e) => setMaxDailyPatients(e.target.value)}
            placeholder="e.g. 10"
          />
        </div>
        <button type="submit">Add Doctor</button>
      </form>
    </div>
  );
};

export default AddDoctorForm;

