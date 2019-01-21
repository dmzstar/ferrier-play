package models

import io.ebean.{Finder, Model}
import javax.persistence.Entity
import javax.persistence._

@Entity
class Home extends Model{

  @Id
  var id:Long = _
  var name:String = _

}

object Home {
  val find = new Finder[Long, User](classOf[User])
}
