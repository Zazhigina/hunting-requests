package ru.zazhig.getway.declaration;

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
