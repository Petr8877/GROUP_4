CREATE TABLE IF NOT EXISTS app.vote_genre
(
    voice_id bigserial,
    genres_id bigint NOT NULL,
    CONSTRAINT gen1_id_genre_id_user_key UNIQUE (genres_id, voice_id),
    CONSTRAINT gen1_id_genre_fkey FOREIGN KEY (genres_id)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT gen1_id_user_fkey FOREIGN KEY (voice_id)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)