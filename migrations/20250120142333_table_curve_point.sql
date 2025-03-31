CREATE TABLE curve_point (
  id UUID PRIMARY KEY NOT NULL DEFAULT UUID(),
  curve_id TINYINT,
  as_of_date TIMESTAMP,
  term DOUBLE,
  value DOUBLE,
  creation_date timestamp
);
