package io.github.plastix.buzz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LiveHints(
    val remainingWordCount: Int,
    val remainingPoints: Int,
    val remainingPangrams: Int,
    val remainingPerfectPangrams: Int,
    val remainingWordsStartingWithLetter: List<LetterStats>,
    val remainingWordsCountOfLength: List<Pair<WordLength, Int>>,
    val twoLetterList: List<List<Pair<String, Int>>>
) : Parcelable

@JvmInline
value class WordLength(val value: Int)

inline val Int.wl get() = WordLength(this)

@Parcelize
data class LetterStats(
    val letter: Char,
    val remainingWordsOfLength: List<Pair<WordLength, Int>>,
    val totalRemainingWordsCount: Int
) : Parcelable

fun solveLiveHints(
    pangrams: Set<String>,
    answers: Set<String>,
    discoveredWords: Set<String>,
): LiveHints {
    val remainingWords = answers - discoveredWords
    val remainingPangrams = pangrams - discoveredWords

    val twoLetterList = remainingWords.groupingBy { it.take(2) }.eachCount().toSortedMap().toList()
        .groupBy { it.first.first() }.toSortedMap().values.toList()

    val remainingWordsStartingWithLetter = remainingWords.groupBy { it.first() }.toSortedMap().map { (letter, words) ->
        val remainingWordsOfLength = words.groupingBy { it.length }.eachCount().toSortedMap().toList().map { it.first.wl to it.second }
        val totalRemainingWordsCount = remainingWordsOfLength.sumOf { it.second }
        LetterStats(letter, remainingWordsOfLength, totalRemainingWordsCount)
    }

    val remainingWordsCountOfLength = remainingWordsStartingWithLetter.map { it.remainingWordsOfLength }
        .flatten().groupBy { it.first }.mapValues { (_, v) ->
            v.sumOf { it.second }
        }.toSortedMap { a, b -> a.value.compareTo(b.value) }.toList()

    return LiveHints(
        remainingWordCount = remainingWords.size,
        remainingPoints = remainingWords.sumOf { word ->
            when {
                word.length == 4 -> 1
                word in pangrams -> word.length + 7
                else -> word.length
            }
        },
        remainingPangrams = remainingPangrams.size,
        remainingPerfectPangrams = remainingPangrams.count { it.length == 7 },
        remainingWordsStartingWithLetter = remainingWordsStartingWithLetter,
        remainingWordsCountOfLength = remainingWordsCountOfLength,
        twoLetterList = twoLetterList,
    )
}
