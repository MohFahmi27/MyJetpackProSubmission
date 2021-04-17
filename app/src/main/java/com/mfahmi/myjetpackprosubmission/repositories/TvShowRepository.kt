package com.mfahmi.myjetpackprosubmission.repositories

import com.mfahmi.myjetpackprosubmission.models.TvShowEntity

object TvShowRepository {
    fun getTvShowData(): ArrayList<TvShowEntity> {
        return arrayListOf(
            TvShowEntity(
                88396,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                7.8,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                listOf(
                    "Sci-Fi & Fantasy", " Action & Adventure", "Drama", "War & Politics"
                )
            ),
            TvShowEntity(
                791373,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "The Good Doctor",
                "2017-09-25",
                8.6,
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                listOf(
                    "Drama"
                )
            ),
            TvShowEntity(
                60735,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "The Flash",
                "2014-10-07",
                7.5,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                listOf(
                    "Drama", "Sci-Fi & Fantasy"
                )
            ),
            TvShowEntity(
                69050,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "Riverdale",
                "2017-01-26",
                8.6,
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                listOf(
                    "Mystery", "Drama", "Crime"
                )
            ),
            TvShowEntity(
                1416,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "Grey's Anatomy",
                "2005-03-27",
                8.2,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                listOf(
                    "Drama"
                )
            ),
            TvShowEntity(
                85271,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "WandaVision",
                "2021-01-15",
                8.4,
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                listOf(
                    "Sci-Fi & Fantasy", "Mystery", "Drama"
                )
            ),
            TvShowEntity(
                95557,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Invincible",
                "2021-03-26",
                8.9,
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                listOf(
                    "Animation", "Action & Adventure", "Drama"
                )
            ),
            TvShowEntity(
                120168,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                "Who Killed Sara?",
                "2021-03-24",
                7.8,
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                listOf(
                    "Drama", "Crime", "Mystery"
                )
            ),
            TvShowEntity(
                1402,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "The Walking Dead",
                "2010-10-31",
                8.1,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                listOf(
                    "Action & Adventure", "Drama", "Sci-Fi & Fantasy"
                )
            ),
            TvShowEntity(
                63174,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "Lucifer",
                "2016-01-25",
                8.5,
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                listOf(
                    "Crime", "Sci-Fi & Fantasy"
                )
            )
        )
    }
}