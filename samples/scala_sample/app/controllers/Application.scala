package controllers

import play.api._
import play.api.mvc._
import views.ThymeleafModule

object Application extends Controller with ThymeleafModule {

  def index = Action {
//    Ok(views.html.index("Your new application is ready."))
    Ok(view("index"))
  }

}