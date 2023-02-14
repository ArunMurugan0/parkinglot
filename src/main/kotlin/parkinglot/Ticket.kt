package parkinglot

import utils.Counter
import java.util.*

data class Ticket(val parkingSpot: ParkingSpot, val entryDateTime: Date) {
    val ticketNumber = TicketNumber(Counter.next())
}