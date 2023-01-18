create TABLE IF NOT EXISTS app.vote_artists
(
    id_user bigint NOT NULL,
    id_artist bigint NOT NULL,
    CONSTRAINT vote1a_id_user_key UNIQUE (id_user),
    CONSTRAINT vote1a_id_artist_fkey FOREIGN KEY (id_artist)
        REFERENCES app.artists (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION,
    CONSTRAINT vote1a_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
)