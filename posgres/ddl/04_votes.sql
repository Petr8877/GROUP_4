CREATE TABLE IF NOT EXISTS app.votes
(
    id bigint NOT NULL DEFAULT nextval('app.votes_id_seq'::regclass),
    dt_create timestamp without time zone,
    about text COLLATE pg_catalog."default" NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    key bigint NOT NULL,
    auth boolean NOT NULL,
    CONSTRAINT votes_pkey PRIMARY KEY (id)
)