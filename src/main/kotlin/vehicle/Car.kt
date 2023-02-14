package vehicle

import parkinglot.VehicleType

class Car: Vehicle() {
    override fun getVehicleType(): VehicleType {
        return VehicleType.LIGHT_FOUR_WHEELER
    }
}