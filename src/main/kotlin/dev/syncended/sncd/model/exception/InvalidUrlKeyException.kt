package dev.syncended.sncd.model.exception

import java.lang.IllegalArgumentException

class InvalidUrlKeyException(key: String): IllegalArgumentException("Url key is invalid (key: $key)")