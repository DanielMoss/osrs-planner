package ddm.ui.model.player.item

object Depository {
  sealed trait ID

  object ID {
    case object Inventory extends ID
    case object Bank extends ID

    sealed trait EquipmentSlot extends ID
    case object HeadSlot extends EquipmentSlot
    case object CapeSlot extends EquipmentSlot
    case object NeckSlot extends EquipmentSlot
    case object AmmunitionSlot extends EquipmentSlot
    case object WeaponSlot extends EquipmentSlot
    case object ShieldSlot extends EquipmentSlot
    case object BodySlot extends EquipmentSlot
    case object LegsSlot extends EquipmentSlot
    case object HandsSlot extends EquipmentSlot
    case object FeetSlot extends EquipmentSlot
    case object RingSlot extends EquipmentSlot
  }

  val bank: Depository =
    Depository(
      Depository.ID.Bank,
      capacity = 820,
      stackLimit = Int.MaxValue,
      stackAll = true,
      contents = Map(
        Item.ID("995") -> 25 // Coins
      ),
      columns = 8,
      minRows = 6
    )

  val inventory: Depository =
    Depository(
      Depository.ID.Inventory,
      capacity = 28,
      stackLimit = Int.MaxValue,
      stackAll = false,
      contents = Map(
        Item.ID("1351") -> 1, // Bronze axe
        Item.ID("1265") -> 1, // Bronze pickaxe
        Item.ID("590") -> 1, // Tinderbox
        Item.ID("303") -> 1, // Small fishing net
        Item.ID("315") -> 1, // Shrimps
        Item.ID("1205") -> 1, // Bronze dagger
        Item.ID("1277") -> 1, // Bronze sword
        Item.ID("1171") -> 1, // Wooden shield
        Item.ID("841") -> 1, // Shortbow
        Item.ID("882") -> 25, // Bronze arrow
        Item.ID("556") -> 25, // Air rune
        Item.ID("558") -> 15, // Mind rune
        Item.ID("1925") -> 1, // Bucket
        Item.ID("1931") -> 1, // Pot
        Item.ID("2309") -> 1, // Bread
        Item.ID("555") -> 6, // Water rune
        Item.ID("557") -> 4, // Earth rune
        Item.ID("559") -> 2 // Body rune
      ),
      columns = 4,
      minRows = 7
    )

  def equipmentSlot(id: ID.EquipmentSlot): Depository =
    Depository(
      id,
      capacity = 1,
      stackLimit = Int.MaxValue,
      stackAll = false,
      contents = Map.empty,
      columns = 1,
      minRows = 1
    )
}

final case class Depository(
  id: Depository.ID,
  capacity: Int,
  stackLimit: Int,
  stackAll: Boolean,
  contents: Map[Item.ID, Int],
  columns: Int,
  minRows: Int
)
