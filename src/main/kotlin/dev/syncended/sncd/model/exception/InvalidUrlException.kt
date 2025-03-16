package dev.syncended.sncd.model.exception

class InvalidUrlException(url: String): IllegalArgumentException("Url is invalid (url: $url)")