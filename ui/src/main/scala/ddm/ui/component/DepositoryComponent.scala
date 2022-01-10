package ddm.ui.component

import ddm.ui.model.item.{Depository, Item}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._

object DepositoryComponent {
  def apply(depository: Depository): Unmounted[Depository, Unit, Unit] =
    ScalaComponent
      .builder[Depository]
      .render_P(d =>
        <.div(
          <.p(d.name),
          <.div(
            destack(d).zipWithIndex.toTagMod { case ((item, count), index) =>
              val optionalCount = if (count == 1) "" else s"$count x "
              <.p(s"${index + 1}. $optionalCount${item.name}")
            }
          )
        )
      )
      .build
      .apply(depository)

  private def destack(depository: Depository): List[(Item, Int)] =
    depository.contents.toList.flatMap {
      case (item, count) if item.stackable || depository.stackAll =>
        List((item, count))
      case (item, count) =>
        List.fill(count)((item, 1))
    }
}
