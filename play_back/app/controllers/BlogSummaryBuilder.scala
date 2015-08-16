package controllers

import qhfu.blog.Blog;

object BlogSummaryBuilder {
  /**
   * Build the HTML of Blog summary
   *
   */
  def build(blog: Blog): String = {
    var sb: StringBuilder = new StringBuilder();
    sb ++= """<div class="content">""";
    sb ++= buildTitle(blog.title, blog.id);
    sb ++= summary(blog);
    sb ++= """</div>""";
    sb.toString();
  }

  private def summary(blog: Blog): String = {
    var sb: StringBuilder = new StringBuilder();
    sb ++= """<p class="meta">""";
    sb ++= blog.date;
    sb ++= "</[>";
    sb ++= """<p>""";
    sb ++= blog.content;
    sb ++= "</p>"
    sb.toString;

  }

  private def buildSummary(summary: String): String = {
    var sb: StringBuilder = new StringBuilder();
    sb ++= """<div>""";
    sb ++= summary;
    sb ++= """</div>""";
    sb.toString();
  }

  private def buildTitle(title: String, id: String): String = {
    var sb: StringBuilder = new StringBuilder();
    sb ++= """<h2><a href="/posts?id=""";
    sb ++= id;
    sb ++= """">""";
    sb ++= title;
    sb ++= """</a></h2>""";
    sb.toString();
  }
}