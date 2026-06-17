const BASE_URL = 'http://localhost:8080/returns';

/**
 * Thin wrapper around fetch for the Returns API.
 * Centralizing this means components never construct URLs or parse
 * error envelopes themselves - one place to change if the API contract shifts.
 */
async function handleResponse(response) {
  if (!response.ok) {
    let message = `Request failed with status ${response.status}`;
    try {
      const errorBody = await response.json();
      message = errorBody.message || message;
    } catch {
      // response body wasn't JSON; fall back to the generic message
    }
    throw new Error(message);
  }
  return response.json();
}

export async function createReturn(payload) {
  const response = await fetch(BASE_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });
  return handleResponse(response);
}

export async function getAllReturns() {
  const response = await fetch(BASE_URL);
  return handleResponse(response);
}

export async function getReturnById(id) {
  const response = await fetch(`${BASE_URL}/${id}`);
  return handleResponse(response);
}

export async function getReturnsByStatus(status) {
  const response = await fetch(`${BASE_URL}/status/${status}`);
  return handleResponse(response);
}
