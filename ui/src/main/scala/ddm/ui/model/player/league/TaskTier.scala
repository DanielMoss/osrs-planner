package ddm.ui.model.player.league

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

sealed trait TaskTier {
  def points: Int
  def expectedRenown: Int
}

object TaskTier {
  case object Easy extends TaskTier {
    val points: Int = 5
    val expectedRenown: Int = 1
  }

  case object Medium extends TaskTier {
    val points: Int = 10
    val expectedRenown: Int = 2
  }

  case object Hard extends TaskTier {
    val points: Int = 50
    val expectedRenown: Int = 3
  }

  case object Elite extends TaskTier {
    val points: Int = 125
    val expectedRenown: Int = 4
  }

  case object Master extends TaskTier {
    val points: Int = 250
    val expectedRenown: Int = 5
  }

  val all: Set[TaskTier] =
    Set(Easy, Medium, Hard, Elite, Master)

  implicit val codec: Codec[TaskTier] = deriveCodec[TaskTier]
}
