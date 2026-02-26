import React from 'react';

const DoctorList = ({ doctors }) => {
  return (
    <div className="card">
      <h2>Doctor Listing</h2>
      {doctors.length === 0 ? (
        <p>No doctors available. Please add a doctor.</p>
      ) : (
        <table className="doctor-table">
          <thead>
            <tr>
              <th>Doctor ID</th>
              <th>Specialization</th>
              <th>Max Daily Patients</th>
              <th>Current Appointments</th>
            </tr>
          </thead>
          <tbody>
            {doctors.map((doc) => (
              <tr key={doc.doctorId}>
                <td>{doc.doctorId}</td>
                <td>{doc.specialization}</td>
                <td>{doc.maxDailyPatients}</td>
                <td>{doc.currentAppointments}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default DoctorList;

