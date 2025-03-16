package dev.syncended.sncd.model.exception

class BrokenEncodingException(rawValue: String) :
    IllegalArgumentException("Encoded value has wring chars (value: $rawValue)")