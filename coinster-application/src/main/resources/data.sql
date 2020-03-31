INSERT INTO users (user_id, username, password, plan, currency, role) VALUES
  (1, 'zsofec', '$2y$12$I9jJ6Ptp.2HtMaaDgXHAiOCysOKOO3aDD7TX6VQUeP88VaEOrwAIq', 'premium', 'Ft', 'BASIC'),
  (2, 'adika', '', 'regular', 'USD', 'BASIC');

INSERT INTO incomes (id, user_id, amount, note, currency, created_at) VALUES
  (3, 1, 1234, 'found', 'Ft', CURRENT_TIMESTAMP ),
  (4, 2, 2000, 'wage', 'USD', CURRENT_TIMESTAMP );