INSERT INTO users (user_id, username, password, plan, currency, role) VALUES
  (1, 'zsofec', '$2y$12$I9jJ6Ptp.2HtMaaDgXHAiOCysOKOO3aDD7TX6VQUeP88VaEOrwAIq', 'premium', 'Ft', 'BASIC'),
  (2, 'adika', '$2y$12$gxYmg3khy5yt93peTLcOSuyEUJcc3FpZG3cyedInmyUjTMazYRvve', 'regular', 'USD', 'BASIC');

INSERT INTO incomes (id, owner, amount, note, currency, created_at) VALUES
  (1, 'zsofec', 10000, 'koszi nagyi', 'Ft', CURRENT_TIMESTAMP),
  (2, 'zsofec', 1234, '', 'Ft', CURRENT_TIMESTAMP);

INSERT INTO spendings (id, owner, amount, currency, planned, category, created_at) VALUES
  (1, 'zsofec', 3000, 'Ft', TRUE, 'groceries', CURRENT_TIMESTAMP),
  (2, 'adika', 100, 'USD', FALSE, 'fashion', CURRENT_TIMESTAMP);