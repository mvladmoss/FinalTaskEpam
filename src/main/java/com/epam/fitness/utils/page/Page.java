package com.epam.fitness.utils.page;

public enum Page {

    HOME_PAGE("/WEB-INF/home.jsp"),
    GYP_PHOTOS_PAGE("/WEB-INF/gymPhotos.jsp"),
    LOGIN("/WEB-INF/login.jsp"),
    MENU("/WEB-INF/menu.jsp"),
    ORDER_PAGE("/WEB-INF/orderPage.jsp"),
    CLIENT_EXERCISE_PAGE("/WEB-INF/client/clientExercise.jsp"),
    CLIENT_NUTRITION_PAGE("/WEB-INF/client/clientNutrition.jsp"),
    CLIENT_PROFILE_PAGE("/WEB-INF/client/clientProfile.jsp"),
    COACHES_CLIENTS_PAGE("/WEB-INF/coach/coachClients.jsp"),
    COACH_COMMENTS_PAGE("/WEB-INF/coach/coachCommentsPage.jsp"),
    NO_ACCESS_PAGE("/WEB-INF/noAccessPage.jsp"),
    ALL_COACHES_PAGE("/WEB-INF/coach/allCoaches.jsp");

    private final String value;

    Page(String value) {
        this.value = value;
    }

    public String getPage() {
        return value;
    }
}
