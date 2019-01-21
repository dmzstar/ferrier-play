package models.cms

import io.ebean.{Finder, Model}
import javax.persistence._
import models.BaseModel

@Entity
class Article extends BaseModel{

  @Id
  var id:Long = _
  var code:String = _
  var title:String = _
  @Lob
  var content:String = _
  var description:String = _
  var status = ContentStatus.INIT
  @OneToMany(mappedBy="article")
  var comments:java.util.Collection[ArticleComment] = new java.util.ArrayList

  def publish = {
    status = ContentStatus.PUBLISHED
    save()
  }

  def lock = {
    status = ContentStatus.LOCK
    save()
  }

}

object Article{
  val find = new Finder[Long, Article](classOf[Article])
}
