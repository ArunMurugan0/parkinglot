package parkinglot

import exceptions.IllegalOperationException
import exceptions.ParkingSpotNotAvailableException
import vehicle.Vehicle
import java.util.*


abstract class ParkingLot(parkingSpotCountConfig: ParkingSpotCountForEachTypeConfig) {
    private val openTickets = TicketCollection()
    private val freeParkingSpotsPool = ParkingSpotPool(parkingSpotCountConfig)

    protected abstract fun calculateFee(ticket: Ticket, exitDateTime: Date): Long

    private fun isParkingSpotNotAvailable(parkingSpotType: ParkingSpotType) =
        !freeParkingSpotsPool.isAvailable(parkingSpotType)

    private fun markTicketAsClosed(ticket: Ticket) {
        openTickets.remove(ticket)
    }

    private fun releaseParkingSpot(parkingSpot: ParkingSpot) {
        freeParkingSpotsPool.addBack(parkingSpot)
    }

    private fun markTicketAsOpen(ticket: Ticket) {
        openTickets.add(ticket)
    }

    private fun generateReceipt(ticket: Ticket, exitDateTime: Date) = Receipt(
        ticket = ticket,
        exitDateTime = exitDateTime,
        fee = calculateFee(ticket, exitDateTime)
    )

    private fun generateParkingTicket(parkingSpotType: ParkingSpotType, entryDateTime: Date): Ticket {
        if (isParkingSpotNotAvailable(parkingSpotType)) {
            throw ParkingSpotNotAvailableException()
        }

        val ticket = Ticket(
            entryDateTime = entryDateTime,
            parkingSpot = freeParkingSpotsPool.acquireParkingSpotOf(parkingSpotType)
        )

        markTicketAsOpen(ticket)

        return ticket
    }

    fun generateParkingFeeReceipt(ticketNumber: TicketNumber, exitDateTime: Date): Receipt {
        val ticket = openTickets.getOrNull(ticketNumber)
            ?: throw IllegalOperationException("Attempted to generate fee receipt for unknown ticket")

        markTicketAsClosed(ticket)
        releaseParkingSpot(ticket.parkingSpot)

        return generateReceipt(ticket, exitDateTime)
    }

    fun generateParkingTicket(vehicle: Vehicle, entryDateTime: Date): Ticket {
        return generateParkingTicket(
            parkingSpotType = vehicle.getParkingSpotType(),
            entryDateTime
        )
    }
}