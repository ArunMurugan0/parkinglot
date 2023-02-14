package parkinglot

import exceptions.ParkingSpotNotAvailableException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParkingSpotPoolTest{

    @Test
    fun `it should provide a parking spot when available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            VehicleType.TWO_WHEELER to 1,
            VehicleType.LIGHT_FOUR_WHEELER to 2,
        ))

        val parkingSpotPool = ParkingSpotPool(config)

        val parkingSpot = parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)

        assertEquals(VehicleType.TWO_WHEELER, parkingSpot.vehicleType)
    }

    @Test
    fun `it should throw exception if parking spot is not available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            VehicleType.TWO_WHEELER to 1,
        ))
        val parkingSpotPool = ParkingSpotPool(config)
        parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)

        assertThrows<ParkingSpotNotAvailableException> {
            parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)
        }
        assertThrows<ParkingSpotNotAvailableException> {
            parkingSpotPool.acquireParkingSpotOf(VehicleType.HEAVY_FOUR_WHEELER)
        }
    }

    @Test
    fun `it should return true if parking spot is available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            VehicleType.TWO_WHEELER to 2,
        ))
        val parkingSpotPool = ParkingSpotPool(config)
        parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)

        assertEquals(true, parkingSpotPool.isAvailable(VehicleType.TWO_WHEELER))
    }

    @Test
    fun `it should return false if parking spot is not available`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            VehicleType.TWO_WHEELER to 1,
        ))
        val parkingSpotPool = ParkingSpotPool(config)
        parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)

        assertEquals(false, parkingSpotPool.isAvailable(VehicleType.TWO_WHEELER))
        assertEquals(false, parkingSpotPool.isAvailable(VehicleType.HEAVY_FOUR_WHEELER))
    }

    @Test
    fun `it should be available after adding back`() {
        val config = ParkingSpotCountForEachTypeConfig(mapOf(
            VehicleType.TWO_WHEELER to 3,
        ))
        val parkingSpotPool = ParkingSpotPool(config)

        val parkingSpotOne = parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)
        parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)
        parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)

        parkingSpotPool.addBack(parkingSpotOne)
        val actualParkingSpotOne = parkingSpotPool.acquireParkingSpotOf(VehicleType.TWO_WHEELER)

        assertEquals(parkingSpotOne, actualParkingSpotOne)
    }
}