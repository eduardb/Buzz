package io.github.plastix.buzz

import org.junit.Assert.assertEquals
import org.junit.Test


/******
R A G I T V Y

WORDS: 31, POINTS: 126, PANGRAMS: 1 (1 Perfect)

4	  5	  6	  7	  Σ
A:	  4	  3	  2	  -	  9
G:	  3	  1	  1	  1	  6
R:	  1	  2	  2	  1	  6
T:	  3	  3	  2	  -	  8
V:	  1	  -	  1	  -	  2
Σ:	 12	  9	  8	  2	 31

Two letter list:

AG-1 AI-1 AR-3 AT-2 AV-2
GI-1 GR-5
RA-6
TA-3 TI-1 TR-4
VA-2
*****/


class LiveHintsTest {

    @Test
    fun `live hints for new board`() {
        assertEquals(
            LiveHints(
                remainingWordCount = 31,
                remainingPoints = 126,
                remainingPangrams = 1,
                remainingPerfectPangrams = 1,
                remainingWordsStartingWithLetter = listOf(
                    LetterStats('a', listOf(4.wl to 4, 5.wl to 3, 6.wl to 2),           9),
                    LetterStats('g', listOf(4.wl to 3, 5.wl to 1, 6.wl to 1, 7.wl to 1),6),
                    LetterStats('r', listOf(4.wl to 1, 5.wl to 2, 6.wl to 2, 7.wl to 1),6),
                    LetterStats('t', listOf(4.wl to 3, 5.wl to 3, 6.wl to 2           ),8),
                    LetterStats('v', listOf(4.wl to 1,            6.wl to 1           ),2),
                ),
                remainingWordsCountOfLength = listOf(4.wl to 12, 5.wl to 9, 6.wl to 8, 7.wl to 2),
                twoLetterList = listOf(
                    listOf("ag" to 1, "ai" to 1, "ar" to 3, "at" to 2, "av" to 2,),
                    listOf("gi" to 1, "gr" to 5,),
                    listOf("ra" to 6,),
                    listOf("ta" to 3, "ti" to 1, "tr" to 4,),
                    listOf("va" to 2,),
                ),
            ),
            solveLiveHints(
                pangrams = setOf("gravity"),
                answers = setOf("gravity", "agar", "airy", "aria", "array", "arty", "atria", "attar", "avatar", "aviary", "girt", "gravy", "gray", "grit", "gritty", "raga", "ragtag", "raita", "rarity", "ratatat", "ratty", "tarry", "tart", "tartar", "tiara", "trait", "tray", "trig", "trivia", "vagary", "vary"),
                discoveredWords = emptySet(),
            )
        )
    }

    @Test
    fun `live hints for solved board`() {
        assertEquals(
            LiveHints(
                remainingWordCount = 0,
                remainingPoints = 0,
                remainingPangrams = 0,
                remainingPerfectPangrams = 0,
                remainingWordsStartingWithLetter = emptyList(),
                remainingWordsCountOfLength = emptyList(),
                twoLetterList = emptyList(),
            ),
            solveLiveHints(
                pangrams = setOf("gravity"),
                answers = setOf("gravity", "agar", "airy", "aria", "array", "arty", "atria", "attar", "avatar", "aviary", "girt", "gravy", "gray", "grit", "gritty", "raga", "ragtag", "raita", "rarity", "ratatat", "ratty", "tarry", "tart", "tartar", "tiara", "trait", "tray", "trig", "trivia", "vagary", "vary"),
                discoveredWords = setOf("gravity", "agar", "airy", "aria", "array", "arty", "atria", "attar", "avatar", "aviary", "girt", "gravy", "gray", "grit", "gritty", "raga", "ragtag", "raita", "rarity", "ratatat", "ratty", "tarry", "tart", "tartar", "tiara", "trait", "tray", "trig", "trivia", "vagary", "vary"),
            )
        )
    }
}
