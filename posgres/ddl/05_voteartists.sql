CREATE TABLE IF NOT EXISTS app.vote_artists
(
    id_user bigint NOT NULL,
    id_artist bigint NOT NULL,
    CONSTRAINT vote_artists_id_user_id_artist_key UNIQUE (id_user)
        INCLUDE(id_artist),
    CONSTRAINT vote_artists_id_artist_fkey FOREIGN KEY (id_artist)
        REFERENCES app.artists (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_artists_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)