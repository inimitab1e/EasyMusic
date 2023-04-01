package com.example.easymusic.core_network.utils.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
