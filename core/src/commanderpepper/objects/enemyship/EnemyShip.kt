package commanderpepper.objects.enemyship

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.baseshapes.Rectangle

class EnemyShip(
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(point, height, width), Drawable {

    private val enemyColor = Color.BLUE

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = enemyColor
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    companion object {
        val shapeRenderer = ShapeRenderer()
    }
}

fun createEnemyShipMatrix(rowSize: Int,
                          columnSize: Int,
                          verticalSpace: YCoordinate,
                          sideMargin: XCoordinate,
                          topMargin: YCoordinate): List<List<EnemyShip>> {
    val shipMatrix = mutableListOf<MutableList<EnemyShip>>()

    val leftMostXCoordinate = sideMargin.copy()
    val rightMostXCoordinate = XCoordinate(Gdx.graphics.width - sideMargin.value)

    val verticalSpaceInBetween = XCoordinate((rightMostXCoordinate.value - leftMostXCoordinate.value) / columnSize)

    val startingYCoordinate = YCoordinate(Gdx.graphics.height - topMargin.value)
    var shipPoint = Point(leftMostXCoordinate, startingYCoordinate)

    val shipHeight = Height(8f)
    val shipWidth = Width(8f)

    for (row in 0 until rowSize) {
        shipMatrix.add(mutableListOf())
        for (column in 0 until columnSize) {
            val enemyShip = EnemyShip(shipPoint, shipHeight, shipWidth)
            shipMatrix[row].add(enemyShip)
            shipPoint = shipPoint.increaseXCoordiante(verticalSpaceInBetween)
        }
        shipPoint = shipPoint.decreaseYCoordiante(verticalSpace)
        shipPoint = Point(leftMostXCoordinate, shipPoint.yCoordinate)
    }

    return shipMatrix
}