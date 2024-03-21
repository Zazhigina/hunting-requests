package ru.zazhig.getway.declaration.model;

public enum Type {
    MASS_EVENTS("Массовые виды"),
    DRAW("Жеребьевочные");
    private String title;

    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
