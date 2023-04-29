CREATE TABLE IF NOT EXISTS orders (
   id SERIAL PRIMARY KEY,
   name TEXT,
   status_id INT references status(id)
);