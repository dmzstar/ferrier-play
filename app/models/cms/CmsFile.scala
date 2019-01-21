package models.cms

import io.ebean.Finder
import javax.persistence.{Entity, Id, Lob}
import models.BaseModel

/**
  * 文件：图片，视频，文档等
  */
@Entity
class CmsFile extends BaseModel{

  @Id
  var id:Long = _
  var code:String = _
  var title:String = _
  @Lob
  var content:String = _
  var description:String = _
  var status = ContentStatus.INIT

  def publish = {
    status = ContentStatus.PUBLISHED
    save()
  }

  def lock = {
    status = ContentStatus.LOCK
    save()
  }

}

object CmsFile{
  val find = new Finder[Long, CmsFile](classOf[CmsFile])
}
