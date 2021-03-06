package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.Height
import commanderpepper.objects.Point
import commanderpepper.objects.Width
import commanderpepper.objects.baseshapes.Rectangle

class FireballBar private constructor(
        private val barColor: Color,
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(point, height, width), Drawable {

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = barColor
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    companion object {

        private val shapeRenderer = ShapeRenderer()

        fun createFireballBar(level: Int, point: Point, height: Height): FireballBar {
            return FireballBar(determineFireballBarColor(level), point, height, determineFireballBarWidth(level))
        }

        fun createFireballBar(level: PlayerFireballLevel, point: Point, height: Height, width: Width): FireballBar {
            return FireballBar(determineFireballBarColor(level), point, height, width)
        }
    }
}

private val limit: Float = 91f

fun determineFireballBarColor(level: Int): Color {
    return when (level) {
        in PlayerFireballLevel.Off().range -> PlayerFireballLevel.Off().color
        in PlayerFireballLevel.Low().range -> PlayerFireballLevel.Low().color
        in PlayerFireballLevel.Medium().range -> PlayerFireballLevel.Medium().color
        else -> PlayerFireballLevel.High().color
    }
}

fun determineFireballBarColor(playerFireballLevel: PlayerFireballLevel): Color {
    return when (playerFireballLevel) {
        is PlayerFireballLevel.Off -> PlayerFireballLevel.Off().color
        is PlayerFireballLevel.Low -> PlayerFireballLevel.Low().color
        is PlayerFireballLevel.Medium -> PlayerFireballLevel.Medium().color
        else -> PlayerFireballLevel.High().color
    }
}

fun determineFireballBarWidth(level: Int): Width {
    return when {
        level >= limit.toInt() -> {
            Width(limit)
        }
        else -> Width(level.toFloat())
    }
}


