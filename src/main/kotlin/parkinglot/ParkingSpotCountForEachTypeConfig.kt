package parkinglot

class ParkingSpotCountForEachTypeConfig(private val parkingSpotCountForEachTypeMap: Map<ParkingSpotType, Long>) {
    fun getParkingSpotCount(type: ParkingSpotType): Long {
        return parkingSpotCountForEachTypeMap.getOrDefault(type,  0)
    }
}