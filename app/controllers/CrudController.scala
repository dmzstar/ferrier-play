package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

trait CrudController extends play.api.i18n.I18nSupport{

  def index;
  def show;
  def edit;
  def save;

}
