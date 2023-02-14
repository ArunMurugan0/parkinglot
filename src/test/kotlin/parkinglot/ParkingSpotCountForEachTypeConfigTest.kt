package parkinglot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParkingSpotCountForEachTypeConfigTest {

    @Test
    fun `It should return the total spot count for each parking spot type`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            ParkingSpotType.TWO_WHEELER to 100,
            ParkingSpotType.LIGHT_FOUR_WHEELER to 40,
            ParkingSpotType.HEAVY_FOUR_WHEELER to 30
        ))

        assertEquals(100, config.getParkingSpotCount(ParkingSpotType.TWO_WHEELER))
        assertEquals(30, config.getParkingSpotCount(ParkingSpotType.HEAVY_FOUR_WHEELER))
        assertEquals( 40, config.getParkingSpotCount(ParkingSpotType.LIGHT_FOUR_WHEELER))
    }

    @Test
    fun `It should return the default value of zero if the config for it is not passed`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            ParkingSpotType.TWO_WHEELER to 100,
        ))

        assertEquals(100, config.getParkingSpotCount(ParkingSpotType.TWO_WHEELER))
        assertEquals(0, config.getParkingSpotCount(ParkingSpotType.HEAVY_FOUR_WHEELER))
        assertEquals( 0, config.getParkingSpotCount(ParkingSpotType.LIGHT_FOUR_WHEELER))
    }
}