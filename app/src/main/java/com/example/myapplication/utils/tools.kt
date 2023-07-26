package com.example.myapplication.utils

class tools {
    companion object {
        fun randomString(length: Int): String {
            val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9') + ' ' + '\t' + '\n'

            return List(length) { charset.random() }
                .joinToString("")
        }
    }
}