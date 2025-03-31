CREATE TABLE rule_name (
  id UUID PRIMARY KEY NOT NULL DEFAULT UUID(),
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sql_str VARCHAR(125),
  sql_part VARCHAR(125)
);
