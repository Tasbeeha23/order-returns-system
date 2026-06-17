import { useState, useCallback } from 'react';
import ReturnForm from './components/ReturnForm';
import ReturnsDashboard from './components/ReturnsDashboard';

export default function App() {
  // Bumping this key after a successful submission re-triggers the dashboard fetch.
  const [refreshKey, setRefreshKey] = useState(0);

  const handleReturnCreated = useCallback(() => {
    setRefreshKey((prev) => prev + 1);
  }, []);

  return (
    <div className="app-shell">
      <header className="app-header">
        <h1>Order Returns System</h1>
        <p>Submit return requests and track their approval status</p>
      </header>

      <main className="app-main">
        <ReturnForm onReturnCreated={handleReturnCreated} />
        <ReturnsDashboard refreshKey={refreshKey} />
      </main>
    </div>
  );
}
