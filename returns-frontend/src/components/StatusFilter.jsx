const STATUS_OPTIONS = [
  { value: 'ALL', label: 'All' },
  { value: 'SUBMITTED', label: 'Submitted' },
  { value: 'APPROVED', label: 'Approved' },
  { value: 'PENDING_REVIEW', label: 'Pending Review' },
  { value: 'REJECTED', label: 'Rejected' },
];

export default function StatusFilter({ selected, onChange }) {
  return (
    <div className="status-filter" role="tablist" aria-label="Filter by status">
      {STATUS_OPTIONS.map((opt) => (
        <button
          key={opt.value}
          type="button"
          role="tab"
          aria-selected={selected === opt.value}
          className={`filter-chip ${selected === opt.value ? 'active' : ''}`}
          onClick={() => onChange(opt.value)}
        >
          {opt.label}
        </button>
      ))}
    </div>
  );
}
