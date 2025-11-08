package org.mawuliazameti.browserx

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import org.mawuliazameti.browserx.ui.theme.BrowserXTheme
import org.mawuliazameti.browserx.ui.theme.CopyLinkReceiver

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrowserXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BrowserApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BrowserApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val url = "https://www.youtube.com/@MawuliAzameti"

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                launchChannel(
                    context = context,
                    url = url
                )
            }
        ) {
            Text(text = "Open Channel")

        }
    }

    //www.youtube.com/@MawuliAzameti
}

fun launchChannel(context: Context, url: String) {

    val color = ContextCompat.getColor(context, R.color.purple_500)
    val colorParams = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(color)
        .build()

    val menuIntent = Intent(context, CopyLinkReceiver::class.java)
    menuIntent.putExtra("URL_KEY", url)

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        menuIntent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )


    val intent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setDefaultColorSchemeParams(colorParams)
        .addMenuItem("Copy link", pendingIntent)
        .setStartAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
        .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        .build()

    intent.launchUrl(context, url.toUri())

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BrowserXTheme {
        BrowserApp()
    }
}


