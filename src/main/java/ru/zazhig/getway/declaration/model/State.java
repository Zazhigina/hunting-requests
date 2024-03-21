package ru.zazhig.getway.declaration.model;

public enum State {
    NEW("НОВАЯ"),
    VERIFIED("ПРОВЕРЕНО"),
    REVIEW("РАССМОТРЕНИЕ"),
    CANCELED("ОТМЕНЕНО");
    private String title;

    State(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
