package com.github.gescof.springbootrabbitmqconcept.model;

public record MessageDto(String message, String sender, long timestamp) {
}
