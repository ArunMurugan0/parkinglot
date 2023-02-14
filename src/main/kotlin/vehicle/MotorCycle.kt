package vehicle

import parkinglot.VehicleType

class MotorCycle: Vehicle() {
    override fun getVehicleType(): VehicleType {
        return VehicleType.TWO_WHEELER
    }
}