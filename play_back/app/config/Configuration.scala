package config

import play.Play

object Configuration {
  var mdBaseDir: String = Play.application().configuration().getString("post.path");
}