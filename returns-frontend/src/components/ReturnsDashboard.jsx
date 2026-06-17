import { useEffect, useState, useCallback } from 'react';
import { getAllReturns, getReturnsByStatus } from '../api/returnApi';
import StatusFilter from './StatusFilter';
import ReturnCard from './ReturnCard';
import Loader from './Loader';
import ErrorMessage from './ErrorMessage';

/**
 * Returns Dashboard.
 * Re-fetches from the backend whenever the status filter changes, rather than
 * filtering client-side, so it exercises the GET /returns/status/{status} endpoint.
 */
export default function ReturnsDashboard({ refreshKey }) {
  const [items, setItems] = useState([]);
  const [status, setStatus] = useState('ALL');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async (selectedStatus) => {
    setLoading(true);
    setError(null);
    try {
      const data =
        selectedStatus === 'ALL'
          ? await getAllReturns()
          : await getReturnsByStatus(selectedStatus);
      setItems(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchData(status);
  }, [status, refreshKey, fetchData]);

  return (
    <div className="card">
      <div className="dashboard-header">
        <h2>Returns Dashboard</h2>
        <StatusFilter selected={status} onChange={setStatus} />
      </div>

      {loading && <Loader label="Loading returns..." />}
      {!loading && error && (
        <ErrorMessage message={error} onRetry={() => fetchData(status)} />
      )}

      {!loading && !error && items.length === 0 && (
        <p className="empty-state">No return requests found for this filter.</p>
      )}

      {!loading && !error && items.length > 0 && (
        <div className="table-wrapper">
          <table>
            <thead>
              <tr>
                <th>Return ID</th>
                <th>Product Name</th>
                <th>Order ID</th>
                <th>Purchase Date</th>
                <th>Status</th>
                <th>Reason</th>
              </tr>
            </thead>
            <tbody>
              {items.map((item) => (
                <ReturnCard key={item.id} item={item} />
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
