CREATE TABLE IF NOT EXISTS app.votes
(
    id bigserial,
    dt_create timestamp without time zone,
    about text NOT NULL,
    CONSTRAINT voites_pkey PRIMARY KEY (id)
)