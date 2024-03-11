package ru.zazhig.getway.request;

public enum RequestStatus {
    NEW("НОВАЯ"),
    CONFIRMED("ПОДТВЕРЖДЕНО"),
    REJECTED("ОТКЛОНЕНО"),
    REVIEW("РАССМОТРЕНИЕ");
    private String title;

    RequestStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
