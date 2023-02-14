package parkinglot

fun ParkingLotFactory(type: ParkingLotType, config: ParkingSpotCountForEachTypeConfig): ParkingLot {
    return when (type) {
        ParkingLotType.MALL -> MallParkingLot(config)
    }
}