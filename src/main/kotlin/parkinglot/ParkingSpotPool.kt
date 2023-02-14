package parkinglot

import exceptions.ParkingSpotNotAvailableException


class ParkingSpotPool(private val parkingSpotCount: ParkingSpotCountForEachTypeConfig) {
    private val acquiredParkingSpotNumbersForEachTypeMap = mutableMapOf<ParkingSpotType, MutableSet<ParkingSpotNumber>>()

    fun acquireParkingSpotOf(parkingSpotType: ParkingSpotType): ParkingSpot {
        val freeParkingSpotNumber = getFreeParkingSpotNumberFor(parkingSpotType)

        markParkingSpotAsAcquired(parkingSpotType, freeParkingSpotNumber)

        return ParkingSpot(
            type = parkingSpotType,
            parkingSpotNumber = freeParkingSpotNumber
        )
    }

    private fun markParkingSpotAsAcquired(parkingSpotType: ParkingSpotType, freeParkingSpotNumber: ParkingSpotNumber) {
        val acquiredParkingSpotNumbers = getAcquiredParkingSpotNumbersFor(parkingSpotType)
        acquiredParkingSpotNumbers.add(freeParkingSpotNumber)
    }

    private fun getFreeParkingSpotNumberFor(parkingSpotType: ParkingSpotType): ParkingSpotNumber {
        val maxParkingSpotNumber = parkingSpotCount.getParkingSpotCount(parkingSpotType)
        val acquiredParkingSpotNumbers = getAcquiredParkingSpotNumbersFor(parkingSpotType)

        for (spotNumber in 1..maxParkingSpotNumber) {
            if (!acquiredParkingSpotNumbers.contains(ParkingSpotNumber(spotNumber))) {
                return ParkingSpotNumber(spotNumber)
            }
        }

        throw ParkingSpotNotAvailableException()
    }

    private fun getAcquiredParkingSpotNumbersFor(type: ParkingSpotType) =
        acquiredParkingSpotNumbersForEachTypeMap.getOrPut(type) { mutableSetOf() }


    fun addBack(parkingSpot: ParkingSpot) {
        getAcquiredParkingSpotNumbersFor(type = parkingSpot.type).removeIf { it == parkingSpot.parkingSpotNumber }
    }

    fun isAvailable(type: ParkingSpotType): Boolean {
        val acquiredSpotCount = getAcquiredParkingSpotNumbersFor(type).size
        val spotCount = parkingSpotCount.getParkingSpotCount(type)

        return acquiredSpotCount < spotCount
    }
}