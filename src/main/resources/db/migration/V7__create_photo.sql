create table photo (
  id bigserial primary key,
  content_type character varying(255),
  data oid,
  size bigint,
  name character varying(255)
);

insert into photo(content_type, data, size, name) values
    ('image/png', 54034, 731, 'rub.png'),
    ('image/png', 54035, 469, 'byn.png'),
    ('image/png', 54036, 63772, 'usd.png');