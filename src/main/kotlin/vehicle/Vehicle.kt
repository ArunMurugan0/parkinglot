package vehicle

import parkinglot.VehicleType

abstract class Vehicle {
    abstract fun getParkingSpotType(): VehicleType
}