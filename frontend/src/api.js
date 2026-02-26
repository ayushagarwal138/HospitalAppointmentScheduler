const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

async function handleResponse(response) {
  const contentType = response.headers.get('Content-Type') || '';
  const data = contentType.includes('application/json')
    ? await response.json()
    : await response.text();

  if (!response.ok) {
    const message = data && data.message ? data.message : 'Request failed';
    throw new Error(message);
  }

  return data;
}

export async function fetchDoctors() {
  const res = await fetch(`${API_BASE_URL}/doctors`);
  return handleResponse(res);
}

export async function addDoctor(payload) {
  const res = await fetch(`${API_BASE_URL}/doctors`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });
  return handleResponse(res);
}

export async function bookAppointment(payload) {
  const res = await fetch(`${API_BASE_URL}/appointments/book`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });
  return handleResponse(res);
}

