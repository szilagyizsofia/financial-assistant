INSERT INTO users (user_id, username, password, plan, currency) VALUES
  (1, 'zsofec', 'nagyontitkos123', 'premium', 'Ft'),
  (2, 'adika', 'Initial01', 'regular', 'USD');

INSERT INTO admins (user_id, username, password) VALUES
  (1, 'nagyfonok', 'admin01'),
  (2, 'adminka', 'admin01');

INSERT INTO incomes (id, owner, amount, note, currency) VALUES
  (1, 'zsofec', 10000, 'koszi nagyi', 'Ft'),
  (2, 'zsofec', 1234, '', 'Ft');

INSERT INTO spendings (id, owner, amount, currency, planned, category) VALUES
  (1, 'zsofec', 3000, 'Ft', TRUE, 'groceries'),
  (2, 'adika', 100, 'USD', FALSE, 'fashion');