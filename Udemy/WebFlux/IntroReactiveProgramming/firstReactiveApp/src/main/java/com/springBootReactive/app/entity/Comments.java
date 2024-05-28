package com.springBootReactive.app.entity;

import java.util.ArrayList;
import java.util.List;

public class Comments {

  private List<String> comments;

  public Comments() {
    this.comments = new ArrayList<>();
  }

  public List<String> getComments() {
    return comments;
  }

  public void setComments(List<String> comments) {
    this.comments = comments;
  }

  public void addComment(String comment) {
    this.comments.add(comment);
  }

  @Override
  public String toString() {
    return "comments=" + comments;
  }
}
