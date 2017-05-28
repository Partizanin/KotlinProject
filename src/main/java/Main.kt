import javafx.scene.control.Button
import javafx.scene.control.Tooltip
import tornadofx.*
import java.time.Duration
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Created by Partizanin on 27.05.2017 23:18:36.
 */
class Main : View("My View") {
    var list: List<Button>? = null
    var tt: Tooltip? = null

    override val root = anchorpane {
        prefWidth = 100.0
        prefHeight = 100.0
        stylesheets.add("styles.css")
        resize(0.0, 0.0)

//        isResizable = false
    }

    init {
        title = "FivesGame"

        with(root) {
            val b1 = button("1") {
                id = "1"
                prefWidth = 45.0
                prefHeight = 45.0
                setOnAction { click() }

            }

            val b2 = button("2") {
                id = "2"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight

                layoutX = prefWidth + 5
                setOnAction { click() }

            }

            val b3 = button("3") {
                id = "3"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight

                layoutX = b2.layoutX + prefWidth + 5

                setOnAction { click() }
            }

            val b4 = button("4") {
                id = "4"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight

                layoutX = b3.layoutX + prefWidth + 5
                setOnAction { click() }

            }

            val b5 = button("5") {
                id = "5"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight


                layoutY += prefHeight + 5

                setOnAction { click() }

            }

            val b6 = button("6") {
                id = "6"

                prefWidth = b5.prefWidth
                prefHeight = b5.prefHeight
                layoutY = b5.layoutY

                layoutX += prefWidth + 5
                setOnAction { click() }

            }

            val b7 = button("7") {
                id = "7"

                prefWidth = b5.prefWidth
                prefHeight = b5.prefHeight
                layoutY = b5.layoutY

                layoutX += b6.layoutX + prefWidth + 5
                setOnAction { click() }

            }

            val b8 = button("8") {
                id = "8"

                prefWidth = b5.prefWidth
                prefHeight = b5.prefHeight
                layoutY = b5.layoutY

                layoutX += b7.layoutX + prefWidth + 5
                setOnAction { click() }

            }

            val b9 = button("9") {
                id = "9"
                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight
                layoutY += b8.layoutY + prefHeight + 5

                setOnAction { click() }

            }

            val b10 = button("10") {
                id = "10"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight
                layoutY = b9.layoutY

                layoutX += b9.layoutX + prefWidth + 5
                setOnAction { click() }

            }

            val b11 = button("11") {
                id = "11"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight
                layoutY = b9.layoutY

                layoutX = b10.layoutX + prefWidth + 5
                setOnAction { click() }

            }

            val b12 = button("12") {
                id = "12"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight
                layoutY = b9.layoutY

                layoutX = b11.layoutX + prefWidth + 5
                setOnAction { click() }

            }

            val b13 = button("13") {
                id = "13"

                prefWidth = b1.prefWidth
                prefHeight = b1.prefHeight


                layoutY += b12.layoutY + prefHeight + 5

                setOnAction { click() }

            }

            val b14 = button("14") {
                id = "14"

                prefWidth = b5.prefWidth
                prefHeight = b5.prefHeight
                layoutY = b13.layoutY

                layoutX += prefWidth + 5
                setOnAction { click() }

            }

            val b15 = button("15") {
                id = "15"

                prefWidth = b5.prefWidth
                prefHeight = b5.prefHeight
                layoutY = b13.layoutY

                layoutX += b14.layoutX + prefWidth + 5
                setOnAction { click() }
                isVisible = false

            }

            val b16 = button("15") {

                id = "16"

                prefWidth = b5.prefWidth
                prefHeight = b5.prefHeight
                layoutY = b13.layoutY

                layoutX += b15.layoutX + prefWidth + 5

                setOnAction { click() }


            }

            list = listOf(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16)

            tt = tooltip {
                isAutoHide = true
                text = "Ви вийграли!"
                styleClass.add("tooltipInfo")
                timerTask { Duration.ofSeconds(3) }
            }
//            newGame()
        }
    }

    fun isWin(): Boolean {
        val isWin = (1..list!!.size - 1).none { list!![it - 1].text.toInt() != it }

        return isWin
    }

    private fun newGame() {
        val shuffleNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).toMutableList()

        shuffle(shuffleNumbers)

        for (index in 0..14) {
            list!![index].text = shuffleNumbers[index].toString()
        }
    }

    fun Button.click() {
        val currentButton = this
        val hiddenButton = getHiddenButton()
        val canChange = when (hiddenButton.id.toInt()) {
            (currentButton.id.toInt() + 4) -> true
            (currentButton.id.toInt() - 4) -> true
            (currentButton.id.toInt() - 1) -> true
            (currentButton.id.toInt() + 1) -> true
            else -> {
                false
            }
        }

        if (canChange) {
            currentButton.isVisible = false
            hiddenButton.isVisible = true
            hiddenButton.text = currentButton.text
        }

        if (isWin()) {
//            root.isDisable = true
            tt!!.show(currentWindow)
        }
    }

    private fun getHiddenButton(): Button {

        list!!.filterNot { it.isVisible }.forEach { return it }

        return button {}
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

}
