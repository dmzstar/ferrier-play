package models

import io.ebean.{Finder, Model}
import javax.persistence.Entity
import javax.persistence._

@Entity
@Table(name="sec_group")
class Group extends Model{

  @Id
  var id:Long = _
  var name:String = _

}

object Group {
  val find = new Finder[Long, Group](classOf[Group])
}