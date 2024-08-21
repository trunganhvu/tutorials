package com.example.springjparest.manytomany.simplepkextracolumn.service;

import com.example.springjparest.manytomany.simplepkextracolumn.model.Reviewer;

public interface ReviewerService {

    Reviewer validateAndGetReviewer(String id);

    Reviewer saveReviewer(Reviewer reviewer);

    void deleteReviewer(Reviewer reviewer);
}
