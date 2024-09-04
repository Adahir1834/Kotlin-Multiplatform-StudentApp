import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getEstudianteDatabase

fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getEstudianteDatabase().estudianteDao()
    }
    App(dao)
}
