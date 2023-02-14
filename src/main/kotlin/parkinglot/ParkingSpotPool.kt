package parkinglot

import exceptions.ParkingSpotNotAvailableException


class ParkingSpotPool(private val parkingSpotCount: ParkingSpotCountForEachTypeConfig) {
    private val acquiredParkingSpotNumbersForEachTypeMap = mutableMapOf<VehicleType, MutableSet<ParkingSpotNumber>>()

    fun acquireParkingSpotOf(vehicleType: VehicleType): ParkingSpot {
        val freeParkingSpotNumber = getFreeParkingSpotNumberFor(vehicleType)

        markParkingSpotAsAcquired(vehicleType, freeParkingSpotNumber)

        return ParkingSpot(
            vehicleType = vehicleType,
            parkingSpotNumber = freeParkingSpotNumber
        )
    }

    private fun markParkingSpotAsAcquired(vehicleType: VehicleType, freeParkingSpotNumber: ParkingSpotNumber) {
        val acquiredParkingSpotNumbers = getAcquiredParkingSpotNumbersFor(vehicleType)
        acquiredParkingSpotNumbers.add(freeParkingSpotNumber)
    }

    private fun getFreeParkingSpotNumberFor(vehicleType: VehicleType): ParkingSpotNumber {
        val maxParkingSpotNumber = parkingSpotCount.getParkingSpotCount(vehicleType)
        val acquiredParkingSpotNumbers = getAcquiredParkingSpotNumbersFor(vehicleType)

        for (spotNumber in 1..maxParkingSpotNumber) {
            if (!acquiredParkingSpotNumbers.contains(ParkingSpotNumber(spotNumber))) {
                return ParkingSpotNumber(spotNumber)
            }
        }

        throw ParkingSpotNotAvailableException()
    }

    private fun getAcquiredParkingSpotNumbersFor(type: VehicleType) =
        acquiredParkingSpotNumbersForEachTypeMap.getOrPut(type) { mutableSetOf() }


    fun addBack(parkingSpot: ParkingSpot) {
        getAcquiredParkingSpotNumbersFor(type = parkingSpot.vehicleType).removeIf { it == parkingSpot.parkingSpotNumber }
    }

    fun isAvailable(type: VehicleType): Boolean {
        val acquiredSpotCount = getAcquiredParkingSpotNumbersFor(type).size
        val spotCount = parkingSpotCount.getParkingSpotCount(type)

        return acquiredSpotCount < spotCount
    }
}