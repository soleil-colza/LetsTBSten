package com.example.tbstenjam.webapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.ResourceEnvironment
import org.jetbrains.compose.resources.getFontResourceBytes
import org.jetbrains.compose.resources.rememberResourceEnvironment

/**
 * デフォルトだと、使用されるフォントの都合で日本語が表示できないワークアラウンド
 * refs: https://github.com/JetBrains/compose-multiplatform/issues/3967#issuecomment-1846879073
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun FontProvider(
    content: @Composable () -> Unit,
) {
    val defaultTextStyle = LocalTextStyle.current
    var loadedTextStyle by remember { mutableStateOf<TextStyle?>(null) }
    val resEnvironment = rememberResourceEnvironment()
    LaunchedEffect(Unit) {
        println("start load")
        val fontFamily = loadFont(resEnvironment)
        println("finish load")
        loadedTextStyle = defaultTextStyle.copy(
            fontFamily = fontFamily,
        )
    }

    val textStyle = loadedTextStyle ?: defaultTextStyle
    CompositionLocalProvider(
        LocalTextStyle provides textStyle
    ) {
        MaterialTheme(
            typography = Typography(
                displayLarge = MaterialTheme.typography.displayLarge.merge(textStyle),
                displayMedium = MaterialTheme.typography.displayMedium.merge(textStyle),
                displaySmall = MaterialTheme.typography.displaySmall.merge(textStyle),
                headlineLarge = MaterialTheme.typography.headlineLarge.merge(textStyle),
                headlineMedium = MaterialTheme.typography.headlineMedium.merge(textStyle),
                headlineSmall = MaterialTheme.typography.headlineSmall.merge(textStyle),
                titleLarge = MaterialTheme.typography.titleLarge.merge(textStyle),
                titleMedium = MaterialTheme.typography.titleMedium.merge(textStyle),
                titleSmall = MaterialTheme.typography.titleSmall.merge(textStyle),
                bodyLarge = MaterialTheme.typography.bodyLarge.merge(textStyle),
                bodyMedium = MaterialTheme.typography.bodyMedium.merge(textStyle),
                bodySmall = MaterialTheme.typography.bodySmall.merge(textStyle),
                labelLarge = MaterialTheme.typography.labelLarge.merge(textStyle),
                labelMedium = MaterialTheme.typography.labelMedium.merge(textStyle),
                labelSmall = MaterialTheme.typography.labelSmall.merge(textStyle),
            )
        ) {
            if (loadedTextStyle != null) {
                content()
            } else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
private suspend fun loadFont(resEnvironment: ResourceEnvironment): FontFamily {
    val regular = getFontResourceBytes(resEnvironment, Res.font.NotoSansJP_Regular)
    val bold = getFontResourceBytes(resEnvironment, Res.font.NotoSansJP_Bold)

    return FontFamily(
        Font(identity = "NotoSansJP-Regular", data = regular, weight = FontWeight.Normal),
        Font(identity = "NotoSansJP-Bold", data = bold, weight = FontWeight.Bold),
    )
}
