package com.springBootReactive.app.entity;

public class UserComments {

  private User user;
  private Comments comments;

  public UserComments(User user, Comments comments) {
    this.user = user;
    this.comments = comments;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Comments getComments() {
    return comments;
  }

  public void setComments(Comments comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "UserComments{" +
            "user=" + user +
            ", comments=" + comments +
            '}';
  }
}
