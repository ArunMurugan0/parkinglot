package parkinglot

import exceptions.UnexpectedVehicleType
import utils.DateUtils
import java.time.LocalDateTime

class StadiumParkingLot(parkingSpotCountConfig: ParkingSpotCountForEachTypeConfig) : ParkingLot(parkingSpotCountConfig) {
    override fun calculateFee(ticket: Ticket, exitDateTime: LocalDateTime): Long {
        return when(ticket.parkingSpot.vehicleType) {
            VehicleType.TWO_WHEELER -> calculateFeeForTwoWheeler(ticket, exitDateTime)
            VehicleType.LIGHT_FOUR_WHEELER -> calculateFeeForLightFourWheeler(ticket, exitDateTime)
            else -> throw UnexpectedVehicleType()
        }
    }

    private fun calculateFeeForTwoWheeler(ticket: Ticket, exitDateTime: LocalDateTime): Long {
        val hours = DateUtils.getTotalHoursBetween(ticket.entryDateTime, exitDateTime).toLong()

        return when {
            hours < 4 -> 30
            hours < 12 -> 90
            else -> 90 + (100 * (hours - 11))
        }
    }

    private fun calculateFeeForLightFourWheeler(ticket: Ticket, exitDateTime: LocalDateTime): Long {
        return 2 * calculateFeeForTwoWheeler(ticket, exitDateTime)
    }
}