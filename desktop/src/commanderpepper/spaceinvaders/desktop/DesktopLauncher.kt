package commanderpepper.spaceinvaders.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import commanderpepper.screens.MainScreen
import commanderpepper.spaceinvaders.SpaceInvaders

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
//        LwjglApplication(SpaceInvaders(), config)
        LwjglApplication(MainScreen(), config)
    }
}