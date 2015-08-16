package controllers

import play.api.db._
import play.api.Play.current

object MyDB {

  def main(args: Array[String]) = {
    val conn = DB.getConnection()
  }
}
