package vehicle

import parkinglot.VehicleType

class Truck: Vehicle() {
    override fun getVehicleType(): VehicleType {
        return VehicleType.HEAVY_FOUR_WHEELER
    }
}