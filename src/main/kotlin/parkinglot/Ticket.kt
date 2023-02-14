package parkinglot

import utils.Counter
import java.time.LocalDateTime
import java.util.*

data class Ticket(val parkingSpot: ParkingSpot, val entryDateTime: LocalDateTime) {
    val ticketNumber = TicketNumber(Counter.next())
}