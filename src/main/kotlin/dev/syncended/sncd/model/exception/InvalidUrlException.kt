package dev.syncended.sncd.model.exception

class InvalidUrlException(url: String): Exception("Url is invalid (url: $url)")