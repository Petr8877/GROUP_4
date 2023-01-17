CREATE TABLE IF NOT EXISTS app.vote_genre
(
    id_user integer NOT NULL,
    id_genre integer NOT NULL,
    CONSTRAINT vote_df_pkey PRIMARY KEY (id_user, id_genre),
    CONSTRAINT vote_df_id_genre_fkey FOREIGN KEY (id_genre)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_df_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)