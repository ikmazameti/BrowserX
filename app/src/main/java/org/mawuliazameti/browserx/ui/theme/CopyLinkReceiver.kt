package org.mawuliazameti.browserx.ui.theme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CopyLinkReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val url = intent?.getStringExtra("URL_KEY") ?: return
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager

        clipboard.setPrimaryClip(android.content.ClipData.newPlainText("Copy Url", url))

        Toast.makeText(context, "Link copied", Toast.LENGTH_SHORT).show()


    }
}