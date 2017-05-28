import java.util.*

/**
 * Created by Partizanin on 28.05.2017 17:55:49.
 */

fun main(args: Array<String>) {

    val shuffleNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).toMutableList()

    shuffle(shuffleNumbers)

    println(shuffleNumbers)
}

fun <T : Comparable<T>> shuffle(items: MutableList<T>): List<T> {
    val rg: Random = Random()
    for (i in 0..items.size - 1) {
        val randomPosition = rg.nextInt(items.size)
        val tmp: T = items[i]
        items[i] = items[randomPosition]
        items[randomPosition] = tmp
    }
    return items
}