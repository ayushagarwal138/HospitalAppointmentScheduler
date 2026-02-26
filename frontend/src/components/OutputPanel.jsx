import React from 'react';

const OutputPanel = ({ outputMessage, errorMessage }) => {
  if (!outputMessage && !errorMessage) {
    return (
      <div className="card">
        <h2>Output</h2>
        <p>No actions yet.</p>
      </div>
    );
  }

  return (
    <div className="card">
      <h2>Output</h2>
      {outputMessage && <p className="success">{outputMessage}</p>}
      {errorMessage && <p className="error">{errorMessage}</p>}
    </div>
  );
};

export default OutputPanel;

