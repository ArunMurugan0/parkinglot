package parkinglot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParkingLotFactoryTest {

    @Test
    fun `it should create an instance of MallParkingLot`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf())
        val parkingLot = ParkingLotFactory(ParkingLotType.MALL, config)

        assertTrue(parkingLot is MallParkingLot, "parking lot is expected to be of type MallParking Lot")
    }
}