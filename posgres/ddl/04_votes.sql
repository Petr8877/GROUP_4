CREATE TABLE IF NOT EXISTS app.votes
(
    id bigserial,
    dt_create timestamp without time zone,
    about text NOT NULL,
    mail text NOT NULL,
    CONSTRAINT votes_pkey PRIMARY KEY (id)
)