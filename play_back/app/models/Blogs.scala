package qhfu.blog;

object Blogs {
  def getAllBlogs(): List[Blog] = {
    val blog: Blog = Blog("Introduce to Java GC","2014-11-12", "1","An brief introudction to Java GC"); List[Blog](blog);
  }
}
