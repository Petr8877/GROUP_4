CREATE TABLE IF NOT EXISTS app.votes
(
    id bigserial,
    dt_create timestamp without time zone,
    about text COLLATE pg_catalog."default" NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    key bigint NOT NULL,
    auth boolean NOT NULL,
    CONSTRAINT votes_pkey PRIMARY KEY (id)
)