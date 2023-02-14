package parkinglot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MallParkingLotTest{

    @Test
    fun ` `() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            VehicleType.TWO_WHEELER to 100,
            VehicleType.LIGHT_FOUR_WHEELER to 40,
            VehicleType.HEAVY_FOUR_WHEELER to 30
        ))

        val parkingLot = MallParkingLot(config)


    }
}