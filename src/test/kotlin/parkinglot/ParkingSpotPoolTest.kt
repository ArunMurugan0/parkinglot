package parkinglot

import exceptions.ParkingSpotNotAvailableException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParkingSpotPoolTest{

    @Test
    fun `it should provide a parking spot when available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            ParkingSpotType.TWO_WHEELER to 1,
            ParkingSpotType.LIGHT_FOUR_WHEELER to 2,
        ))

        val parkingSpotPool = ParkingSpotPool(config)

        val parkingSpot = parkingSpotPool.acquireParkingSpotOf(ParkingSpotType.TWO_WHEELER)

        assertEquals(ParkingSpotType.TWO_WHEELER, parkingSpot.type)
    }

    @Test
    fun `it should throw exception if parking spot is not available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            ParkingSpotType.TWO_WHEELER to 1,
        ))
        val parkingSpotPool = ParkingSpotPool(config)
        parkingSpotPool.acquireParkingSpotOf(ParkingSpotType.TWO_WHEELER)

        assertThrows<ParkingSpotNotAvailableException> {
            parkingSpotPool.acquireParkingSpotOf(ParkingSpotType.TWO_WHEELER)
        }
        assertThrows<ParkingSpotNotAvailableException> {
            parkingSpotPool.acquireParkingSpotOf(ParkingSpotType.HEAVY_FOUR_WHEELER)
        }
    }

    @Test
    fun `it should return true if parking spot is available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            ParkingSpotType.TWO_WHEELER to 2,
        ))
        val parkingSpotPool = ParkingSpotPool(config)
        parkingSpotPool.acquireParkingSpotOf(ParkingSpotType.TWO_WHEELER)

        assertEquals(true, parkingSpotPool.isAvailable(ParkingSpotType.TWO_WHEELER))
    }

    @Test
    fun `it should return false if parking spot is not available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            ParkingSpotType.TWO_WHEELER to 1,
        ))
        val parkingSpotPool = ParkingSpotPool(config)
        parkingSpotPool.acquireParkingSpotOf(ParkingSpotType.TWO_WHEELER)

        assertEquals(false, parkingSpotPool.isAvailable(ParkingSpotType.TWO_WHEELER))
        assertEquals(false, parkingSpotPool.isAvailable(ParkingSpotType.HEAVY_FOUR_WHEELER))
    }
}