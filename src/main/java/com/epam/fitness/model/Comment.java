package com.epam.fitness.model;

import java.util.Objects;

public class Comment implements Identifiable {

    private Long id;
    private Long clientId;
    private Long coachId;
    private String commentContent;

    public Comment(Long clientId, Long coachId, String commentContent){
        this.clientId = clientId;
        this.coachId = coachId;
        this.commentContent = commentContent;
    }

    public Comment(Long id, Long clientId, Long coachId, String commentContent){
        this.id = id;
        this.clientId = clientId;
        this.coachId = coachId;
        this.commentContent = commentContent;
    }

    public Comment(){}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commmentContent) {
        this.commentContent = commmentContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return  Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getClientId(), comment.getClientId()) &&
                Objects.equals(getCoachId(), comment.getCoachId()) &&
                Objects.equals(getCommentContent(), comment.getCommentContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getClientId(),getCoachId(),getCommentContent());
    }

}

