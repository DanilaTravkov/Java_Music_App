ALTER TABLE band
ALTER COLUMN description TYPE VARCHAR(1000);

ALTER TABLE band
ALTER COLUMN genre TYPE VARCHAR(50);

UPDATE band
SET date_disbanded = '1994-04-05'
WHERE id = 1;

UPDATE band
SET genre = 'ROCK'
WHERE id = 1;

UPDATE band
SET description = 'Nirvana was an influential American rock band formed in 1987 in Aberdeen, Washington, by singer and guitarist Kurt Cobain and bassist Krist Novoselic. Known for their raw energy and powerful lyrics, Nirvana played a key role in popularizing the grunge genre, blending punk rock and heavy metal with introspective, often dark themes. Their breakthrough came with the release of *Nevermind* in 1991, featuring the iconic single "Smells Like Teen Spirit," which became a generational anthem.

Nirvana''s music reflected Cobain''s troubled emotions and societal disillusionment. Despite their success, the band dissolved in 1994 after Cobain’s tragic death. Today, they are remembered as one of the most influential bands of the 1990s, shaping the sound of alternative rock.'
WHERE id = 1;

INSERT INTO band (name, genre, description, country, date_created, date_disbanded)
VALUES ('Gillian Carter', 'Screamo/Post-Hardcore',
        'Gillian Carter is an American post-hardcore and screamo band formed in 2008 in Florida. Known for their intense, emotional music, the band blends aggressive guitar work, raw vocals, and atmospheric elements. Their sound is characterized by a mix of chaotic, dissonant riffs and melodic interludes, creating a powerful, immersive experience.

Gillian Carter''s lyrics often explore themes of personal struggle, existential angst, and emotional turmoil. The band has developed a dedicated following within the underground scene, gaining recognition for their dynamic live performances and their ability to push the boundaries of their genre. Their discography includes several well-received albums and EPs, contributing to their reputation as a distinctive force in modern post-hardcore.',
        'USA', '2005-01-01', NULL);

