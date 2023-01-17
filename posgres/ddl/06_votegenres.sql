CREATE TABLE IF NOT EXISTS app.votegenres
(
    id_user bigint NOT NULL,
    id_genre bigint,
    CONSTRAINT "voteGenres_id_user_fkey" FOREIGN KEY (id_user)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT votegenres_id_genre_fkey FOREIGN KEY (id_genre)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)