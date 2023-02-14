package parkinglot

class ParkingSpotCountForEachTypeConfig(private val parkingSpotCountForEachTypeMap: Map<VehicleType, Long>) {
    fun getParkingSpotCount(type: VehicleType): Long {
        return parkingSpotCountForEachTypeMap.getOrDefault(type,  0)
    }
}