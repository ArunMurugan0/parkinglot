package parkinglot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class TicketCollectionTest {

    @Test
    fun `it should be able to access all tickets that are added`() {
        val ticketOne =
            Ticket(
                parkingSpot = ParkingSpot(
                    type = ParkingSpotType.TWO_WHEELER,
                    parkingSpotNumber = ParkingSpotNumber(1)
                ),
                Date()
            )
        val ticketTwo = Ticket(
            parkingSpot = ParkingSpot(
                type = ParkingSpotType.HEAVY_FOUR_WHEELER,
                parkingSpotNumber = ParkingSpotNumber(2)
            ),
            Date()
        )

        val tickets = TicketCollection()
        tickets.add(ticketOne)
        tickets.add(ticketTwo)

        assertEquals(ticketOne, tickets.getOrNull(ticketOne.ticketNumber))
        assertEquals(ticketTwo, tickets.getOrNull(ticketTwo.ticketNumber))
    }

    @Test
    fun `it should return null when trying retrieve if the ticket is not found`() {
        val ticketOne =
            Ticket(
                parkingSpot = ParkingSpot(
                    type = ParkingSpotType.TWO_WHEELER, parkingSpotNumber = ParkingSpotNumber(1)
                ),
                Date()
            )

        val tickets = TicketCollection()

        assertEquals(null, tickets.getOrNull(ticketOne.ticketNumber))
    }

    @Test
    fun `it should remove the ticket from the collection`() {
        val ticketOne =
            Ticket(
                parkingSpot = ParkingSpot(
                    type = ParkingSpotType.TWO_WHEELER, parkingSpotNumber = ParkingSpotNumber(1)
                ),
                Date()
            )
        val tickets = TicketCollection()
        tickets.add(ticketOne)
        tickets.remove(ticketOne)

        assertEquals(null, tickets.getOrNull(ticketOne.ticketNumber))
    }
}