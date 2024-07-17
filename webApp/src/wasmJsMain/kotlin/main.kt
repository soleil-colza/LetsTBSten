import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.tbstenjam.shared.App
import com.example.tbstenjam.webapp.FontProvider
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        FontProvider {
            App()
        }
    }
}
