package ddm.ui

import ddm.ui.component.MainComponent
import ddm.ui.model.plan.Step
import ddm.ui.model.player.item.Item
import io.circe.Decoder
import io.circe.parser.decode
import org.scalajs.dom.experimental.Fetch
import org.scalajs.dom.{Event, document}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {
  document.addEventListener[Event]("DOMContentLoaded", _ => setupUI())

  private def setupUI(): Unit =
    withResource[List[Item]](ResourcePaths.itemsJson)(items =>
      withResource[Step](ResourcePaths.planJson) { plan =>
        val itemCache = items.map(i => i.id -> i).toMap
        // Creating a container, since react raises a warning if we render
        // directly into the document body.
        val container = document.createElement("div")
        MainComponent(plan, itemCache).renderIntoDOM(container)
        document.body.appendChild(container)
      }
    )

  private def withResource[T : Decoder](path: String)(f: T => Unit): Unit =
    Fetch
      .fetch(path)
      .toFuture
      .flatMap(_.text().toFuture)
      .map(decode[T])
      .map(_.toTry)
      .flatMap(Future.fromTry)
      .foreach(f)
}
