package vehicle

import parkinglot.ParkingSpotType

abstract class Vehicle {
    abstract fun getParkingSpotType(): ParkingSpotType
}