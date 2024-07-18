package com.example.tbstenjam.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform