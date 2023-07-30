package com.example.myapplication.utils

import android.net.Uri

class tools {
    companion object {
        fun randomString(length: Int): String {
            val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9') + ' ' + '\t' + '\n'

            return List(length) { charset.random() }
                .joinToString("")
        }

        fun resourceToUri(resourceId: Int): Uri = Uri.parse("android.resource://com.example.myapplication/${resourceId}")
    }
}