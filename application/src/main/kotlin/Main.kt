import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import navcontroller.rememberNavController
import screens.schedulePage
import screens.welcomePage
import style.AppTheme

fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Maximized)
    Window(
        state = windowState,
        title = "UW Course Scheduler",
        onCloseRequest = ::exitApplication
    ) {
        AppTheme { App() }
    }
}

@Composable
fun App(){
    val navController by rememberNavController(Screen.WelcomePage.name)
    CustomNavigationHost(navController = navController)
}

/**
 * Screens
 */
enum class Screen(
) {
    WelcomePage,
    SchedulePage
}


@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {

        composable(Screen.WelcomePage.name) {
            welcomePage(navController)
        }

        composable(Screen.SchedulePage.name) {
            schedulePage(navController)
        }

    }.build()
}
