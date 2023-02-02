CREATE TABLE IF NOT EXISTS app.vote_artists
(
    voice_id bigint NOT NULL,
    singer_id bigint NOT NULL,
    CONSTRAINT vote1a_id_user_key UNIQUE (voice_id),
    CONSTRAINT vote1a_id_artist_fkey FOREIGN KEY (singer_id)
        REFERENCES app.artists (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote1a_id_user_fkey FOREIGN KEY (voice_id)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)