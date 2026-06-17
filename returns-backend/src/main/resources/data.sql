-- Sample seed data: a mix of APPROVED, PENDING_REVIEW, and REJECTED outcomes
-- so the dashboard has realistic data to filter/demo immediately on startup.

INSERT INTO return_requests (order_id, product_name, purchase_date, return_reason, item_condition, status, decision_reason, created_at) VALUES
('ORD-1001', 'Wireless Mouse', '2026-06-05', 'Changed my mind', 'UNUSED', 'APPROVED', 'Item is unused and within the 30-day return window', '2026-06-10T09:15:00'),
('ORD-1002', 'Bluetooth Headphones', '2026-05-30', 'Sound quality issue', 'OPENED', 'PENDING_REVIEW', 'Item has been opened - flagged for manual inspection before a final decision', '2026-06-11T14:20:00'),
('ORD-1003', 'Coffee Maker', '2026-04-01', 'Arrived broken', 'DAMAGED', 'REJECTED', 'Item is damaged and not eligible for return', '2026-06-12T10:05:00'),
('ORD-1004', 'Yoga Mat', '2026-03-20', 'No longer needed', 'UNUSED', 'REJECTED', 'Return window expired: 89 days since purchase exceeds the 30-day limit', '2026-06-15T16:45:00'),
('ORD-1005', 'Electric Kettle', '2026-06-12', 'Wrong item shipped', 'UNUSED', 'APPROVED', 'Item is unused and within the 30-day return window', '2026-06-16T08:30:00'),
('ORD-1006', 'Desk Lamp', '2026-06-08', 'Doesn''t match description', 'OPENED', 'PENDING_REVIEW', 'Item has been opened - flagged for manual inspection before a final decision', '2026-06-14T11:50:00');
