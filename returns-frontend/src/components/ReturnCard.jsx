import StatusBadge from './StatusBadge';

export default function ReturnCard({ item }) {
  return (
    <tr>
      <td>#{item.id}</td>
      <td>{item.productName}</td>
      <td>{item.orderId}</td>
      <td>{item.purchaseDate}</td>
      <td>
        <StatusBadge status={item.status} />
      </td>
      <td className="reason-cell">{item.decisionReason}</td>
    </tr>
  );
}
