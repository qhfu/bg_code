package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import models._
import qhfu.blog._;
import play.api.data.Mapping

object Application extends Controller {

  private val LOGGER = Logger.logger;

  def index = Action {
    val post = BlogReader.getLatestPost();
    Ok(views.html.post.render(BlogReader.getLatestPost(), CommentsStorage.getComments(post.date)));
  }

  def blog = Action {
    Ok(views.html.blog.render(BlogReader.getLatestPosts(5)));
  }

  def post(id: String) = Action {
    val post = BlogReader.getPost(id);
    Ok(views.html.post.render(post, CommentsStorage.getComments(post.date)))
  }

  def tags(tag: String) = Action {
    Ok(views.html.tags.render(tag, BlogReader.getPostByTag(tag)))
  }

  def about() = Action {
    Ok(views.html.about.render());
  }

  def comment() = Action { implicit request =>
    val form: Form[Comment] = Form(
      mapping(
        "name" -> text,
        "email" -> email,
        "postName" -> text,
        "content" -> text,
        "postDate" -> text)(Comment.apply)(Comment.unapply))
        
    val formData = form.bindFromRequest().get
    CommentsStorage.addComment(formData);
    val post:Post = BlogReader.getPost(formData.postName);
    
    LOGGER.error("The comment form data: " + formData);
    LOGGER.error("The comment form data 2: " + formData.postName);
    LOGGER.error("The comment form data 2: " + post.date);
    
    Ok(views.html.post.render(post, CommentsStorage.getComments(post.date)))
  }

}
