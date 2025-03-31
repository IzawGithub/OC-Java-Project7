CREATE TABLE rating (
  id UUID PRIMARY KEY NOT NULL DEFAULT UUID(),
  moodys_rating VARCHAR(125),
  sandprating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number TINYINT
);
