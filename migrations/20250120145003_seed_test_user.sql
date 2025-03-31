INSERT INTO user(username, password, fullname, role)
VALUES (
    'JohnDoe',
    -- PasswordJohnDoe
    '$argon2id$v=19$m=16384,t=2,p=1$U2FsdEpvaG5Eb2U$ZUFRW1E1uppirvcUqAjL1w',
    'John Doe',
    'USER'
  ),
(
    'JaneDoe',
    -- PasswordJaneDoe
    '$argon2id$v=19$m=16384,t=2,p=1$U2FsdEphbmVEb2U$4JWoVz4qkPCV8upyM//MrA',
    'Jane Doe',
    'USER'
  );
