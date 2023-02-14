package parkinglot

import utils.DateUtils
import java.time.LocalDateTime

class MallParkingLot(config: ParkingSpotCountForEachTypeConfig) : ParkingLot(config) {
    private fun getHourlyFeeRate(type: VehicleType): Long = when(type) {
        VehicleType.TWO_WHEELER -> 10
        VehicleType.LIGHT_FOUR_WHEELER -> 20
        VehicleType.HEAVY_FOUR_WHEELER -> 50
    }

    override fun calculateFee(ticket: Ticket, exitDateTime: LocalDateTime): Long {
        val totalParkedHours = DateUtils.getTotalHoursBetween(
            startDateTime = ticket.entryDateTime,
            endDateTime = exitDateTime
        )

        return getHourlyFeeRate(ticket.parkingSpot.vehicleType) * totalParkedHours
    }
}