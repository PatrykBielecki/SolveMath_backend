package eu.solvemath.solvemath.domain;

import lombok.Getter;

public enum InformationStatus {
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    LOST("LOST"),
    BROKEN("BROKEN"),
    OTHER("OTHER");

    @Getter
    private final String status;

    InformationStatus(String status) {
        this.status = status;
    }
}
