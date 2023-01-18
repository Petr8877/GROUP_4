create TABLE IF NOT EXISTS app.vote_genre
(
    id_user bigint NOT NULL,
    id_genre bigint NOT NULL,
    CONSTRAINT gen1_id_genre_id_user_key UNIQUE (id_genre, id_user),
    CONSTRAINT gen1_id_genre_fkey FOREIGN KEY (id_genre)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT gen1_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)