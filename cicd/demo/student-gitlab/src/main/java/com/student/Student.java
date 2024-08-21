package com.student;

public record Student(
        int id,
        String name,
        String className,
        int age,
        String address,
        int englishScore,
        int literatureScore,
        int mathScore,
        int physicsScore,
        int chemistryScore
) {}
