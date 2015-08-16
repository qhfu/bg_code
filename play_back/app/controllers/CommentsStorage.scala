package controllers

import models.Comment;

object CommentsStorage {
  var commentsMap: Map[String, List[Comment]] = Map();

  def addComment(comment: Comment) = {
    if (commentsMap.contains(comment.postName)) {
      commentsMap += (comment.postName -> (commentsMap(comment.postName) :+ comment));
    } else {
      commentsMap += (comment.postName -> (List(comment)));
    }
  }

  def getComments(name: String) = {
    if (commentsMap.contains(name)) {
      commentsMap(name)
    } else {
      List[Comment]();
    }
  }
}