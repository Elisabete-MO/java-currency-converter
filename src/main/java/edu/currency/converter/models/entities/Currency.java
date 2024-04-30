package edu.currency.converter.models.entities;

public record Currency(String base_code,
                       String target_code,
                       String conversion_rate,
                       String time_last_update_utc) {}
