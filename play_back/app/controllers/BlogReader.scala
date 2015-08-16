package controllers

import play.Play
import java.io.File
import models.Post
import org.pegdown.PegDownProcessor
import scala.collection.mutable.HashMap
import config.Configuration;
import play.api.Logger

object BlogReader {

  private val LOGGER = Logger.logger;

  private val mdDir = Configuration.mdBaseDir;

  val mdToHtmlProcessor: PegDownProcessor = new PegDownProcessor();

  val posts: Array[String] = allPosts();

  val postCache = HashMap[String, Post]();

  private def readPostFile(postName: String): Array[String] = {
    scala.io.Source.fromFile(mdDir + "/" + postName).getLines.toArray;
  }

  def getPost(name: String): Post = {
    if (!postCache.contains(name)) {
      val data = readPostFile(name + ".md");
      val post = Post(strip(data(0)), strip(data(1)), strip(data(2)), name, createTags(data(3)), strip(data(4)), mdToHtmlProcessor.markdownToHtml(data.drop(5).mkString("\r\n")).toString());
      postCache += (name -> post);
    }
    postCache(name);
  }

  def getLatestPost(): Post = {
    getPost(posts(posts.size - 1));
  }

  def getLatestPosts(n: Int): Array[Post] = {
    val k: Int = if (n > posts.size) posts.size else n;
    (for {
      i <- 0 until k
    } yield {
      getPost(posts(posts.size - 1 - i));
    }).toArray
  }

  def allPosts(): Array[String] = {
    val dir = new File(mdDir)
    dir.listFiles().map(file => file.getName()).filter { x => x.endsWith("md") }.map { name => name.split("\\.")(0) };
  }

  def getPostByTag(tag: String): Array[Post] = {
    posts.map(p => (getPost(p))).filter(p2 => p2.tags.contains(tag)).reverse;
  }

  private def createTags(data: String): Array[String] = {
    val part2 = strip(data);
    part2.split(",").map(s => s.trim()).toArray;
  }

  private def strip(str: String): String = {
    val splited = str.split(":");
    if (splited.size == 2) {
      splited(1)
    } else { "" }
  }

  def main(args: Array[String]): Unit = {
    //    
    val mdToHtmlProcessor: PegDownProcessor = new PegDownProcessor();
    //    val html = mdToHtmlProcessor.markdownToHtml(BlogReader.readPostFile("2015-01-21.md"));
    //    println(html.toString());
    println("hello1");
  }

}
