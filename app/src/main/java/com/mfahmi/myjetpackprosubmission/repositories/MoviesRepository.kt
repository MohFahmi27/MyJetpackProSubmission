package com.mfahmi.myjetpackprosubmission.repositories

import com.mfahmi.myjetpackprosubmission.models.MoviesEntity

object MoviesRepository {
    fun getMoviesData(): ArrayList<MoviesEntity> {
        return arrayListOf(
            MoviesEntity(
                399566,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "Godzilla vs. Kong",
                "2021-03-24",
                8.3,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                listOf(
                    "Action", "Science", "Fiction"
                )
            ),
            MoviesEntity(
                791373,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "Zack Snyder's Justice League",
                "2021-03-18",
                8.5,
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                listOf(
                    "Action", "Adventure", "Fantasy", "Science", "Fiction"
                )
            ),
            MoviesEntity(
                412656,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "Chaos Walking",
                "2021-02-24",
                7.5,
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                listOf(
                    "Science Fiction", "Action", "Adventure", "Thriller"
                )
            ),
            MoviesEntity(
                527774,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "Raya and the Last Dragon",
                "2021-03-03",
                8.3,
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                listOf(
                    "Animation", "Adventure", "Fantasy", "Family", "Action"
                )
            ),
            MoviesEntity(
                587807,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "Tom & Jerry",
                "2021-02-11",
                7.3,
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                listOf(
                    "Comedy", "Family", "Animation"
                )
            ),
            MoviesEntity(
                544401,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg",
                "Cherry",
                "2021-02-26",
                7.6,
                "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
                listOf(
                    "Crime", "Drama"
                )
            ),
            MoviesEntity(
                458576,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "Monster Hunter",
                "2020-12-03",
                7.1,
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                listOf(
                    "Fantasy", "Action", "Adventure"
                )
            ),
            MoviesEntity(
                793723,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "Sentinelle",
                "2021-03-05",
                6.1,
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                listOf(
                    "Thriller", "Action", "Drama"
                )
            ),
            MoviesEntity(
                587996,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                "Below Zero",
                "2021-01-29",
                6.4,
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                listOf(
                    "Action", "Crime", "Thriller"
                )
            ),
            MoviesEntity(
                664767,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4VlXER3FImHeFuUjBShFamhIp9M.jpg",
                "Mortal Kombat Legends: Scorpion's Revenge",
                "2020-04-12",
                8.4,
                "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign over the Mortal Kombat tournament.",
                listOf(
                    "Animation", "Action", "Fantasy"
                )
            )
        )
    }
}