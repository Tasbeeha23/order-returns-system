const STATUS_STYLES = {
  APPROVED: 'badge badge-approved',
  PENDING_REVIEW: 'badge badge-pending',
  REJECTED: 'badge badge-rejected',
  SUBMITTED: 'badge badge-submitted',
};

const STATUS_LABELS = {
  APPROVED: 'Approved',
  PENDING_REVIEW: 'Pending Review',
  REJECTED: 'Rejected',
  SUBMITTED: 'Submitted',
};

export default function StatusBadge({ status }) {
  return (
    <span className={STATUS_STYLES[status] || 'badge'}>
      {STATUS_LABELS[status] || status}
    </span>
  );
}
