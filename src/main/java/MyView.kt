import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import tornadofx.*
import java.util.*

/**
 * Created by Partizanin on 29.05.2017 14:40:05.
 */
class MyView : View("My View") {
    override val root: AnchorPane by fxml("View.fxml")

    val b1: Button by fxid("1")
    val b2: Button by fxid("2")
    val b3: Button by fxid("3")
    val b4: Button by fxid("4")
    val b5: Button by fxid("5")
    val b6: Button by fxid("6")
    val b7: Button by fxid("7")
    val b8: Button by fxid("8")
    val b9: Button by fxid("9")
    val b10: Button by fxid("10")
    val b11: Button by fxid("11")
    val b12: Button by fxid("12")
    val b13: Button by fxid("13")
    val b14: Button by fxid("14")
    val b15: Button by fxid("15")
    val b16: Button by fxid("16")

    var list = listOf(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16)


    init {
        (currentWindow as Stage).isResizable = false
        b15.isVisible = false
        for (button in list) {
            button.setOnAction { click(button) }
        }
    }


    fun click(button: Button) {
        val currentButton = button
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


        if (canChange && !isWin()) {
            currentButton.isVisible = false
            hiddenButton.isVisible = true
            hiddenButton.text = currentButton.text

            if (isWin()) {
                println("win")
//            root.isDisable = true
//        tt!!.show(currentWindow, list!![4].layoutX + 5, list!![4].layoutY)

            }
        }


    }


    fun isWin(): Boolean {
        return (1..list.size - 1).none { list[it - 1].text.toInt() != it }
    }

    private fun getHiddenButton(): Button {

        list.filterNot { it.isVisible }.forEach { return it }

        return button {}
    }

    private fun newGame() {
        val shuffleNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).toMutableList()

        shuffle(shuffleNumbers)

        for (index in 0..14) {
            list[index].text = shuffleNumbers[index].toString()
        }
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



