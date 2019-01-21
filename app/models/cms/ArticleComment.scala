package models.cms

import io.ebean.Finder
import javax.persistence._
import models.BaseModel

@Entity
class ArticleComment extends BaseModel{

  @Id
  var id:Long = _
  @Lob
  var content:String = _
  var description:String = _
  var status = ContentStatus.INIT
  @ManyToOne(optional = false)
  var article:Article = _

  def publish = {
    status = ContentStatus.PUBLISHED
    save()
  }

  def lock = {
    status = ContentStatus.LOCK
    save()
  }

}

object ArticleComment{
  val find = new Finder[Long, ArticleComment](classOf[ArticleComment])
}
