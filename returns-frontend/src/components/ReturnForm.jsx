import { useState } from 'react';
import { createReturn } from '../api/returnApi';

const INITIAL_FORM = {
  orderId: '',
  productName: '',
  purchaseDate: '',
  returnReason: '',
  itemCondition: 'UNUSED',
};

/**
 * Return Request Form.
 * On submit, the backend evaluates eligibility immediately and returns
 * the resulting status + reason, which we surface as inline feedback.
 */
export default function ReturnForm({ onReturnCreated }) {
  const [form, setForm] = useState(INITIAL_FORM);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState(null);
  const [result, setResult] = useState(null);

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setSubmitting(true);
    setError(null);
    setResult(null);

    try {
      const created = await createReturn(form);
      setResult(created);
      setForm(INITIAL_FORM);
      onReturnCreated?.(created);
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div className="card">
      <h2>New Return Request</h2>
      <form onSubmit={handleSubmit} className="return-form">
        <div className="form-row">
          <label htmlFor="orderId">Order ID</label>
          <input
            id="orderId"
            name="orderId"
            type="text"
            value={form.orderId}
            onChange={handleChange}
            placeholder="ORD-1007"
            required
          />
        </div>

        <div className="form-row">
          <label htmlFor="productName">Product Name</label>
          <input
            id="productName"
            name="productName"
            type="text"
            value={form.productName}
            onChange={handleChange}
            placeholder="Wireless Mouse"
            required
          />
        </div>

        <div className="form-row">
          <label htmlFor="purchaseDate">Purchase Date</label>
          <input
            id="purchaseDate"
            name="purchaseDate"
            type="date"
            value={form.purchaseDate}
            onChange={handleChange}
            max={new Date().toISOString().split('T')[0]}
            required
          />
        </div>

        <div className="form-row">
          <label htmlFor="returnReason">Return Reason</label>
          <textarea
            id="returnReason"
            name="returnReason"
            value={form.returnReason}
            onChange={handleChange}
            placeholder="Tell us why you're returning this item"
            rows={3}
            required
          />
        </div>

        <div className="form-row">
          <label htmlFor="itemCondition">Item Condition</label>
          <select
            id="itemCondition"
            name="itemCondition"
            value={form.itemCondition}
            onChange={handleChange}
          >
            <option value="UNUSED">Unused</option>
            <option value="OPENED">Opened</option>
            <option value="DAMAGED">Damaged</option>
          </select>
        </div>

        <button type="submit" className="btn-primary" disabled={submitting}>
          {submitting ? 'Submitting...' : 'Submit Return Request'}
        </button>
      </form>

      {error && <p className="inline-error">⚠ {error}</p>}

      {result && (
        <div className={`result-banner result-${result.status.toLowerCase()}`}>
          <strong>{result.status.replace('_', ' ')}</strong>
          <p>{result.decisionReason}</p>
        </div>
      )}
    </div>
  );
}
