
import org.junit.jupiter.api.Test
import parkinglot.ParkingSpot
import parkinglot.ParkingSpotType
import parkinglot.Ticket
import parkinglot.TicketNumber
import java.util.*
import kotlin.test.assertEquals

class TicketTest {

    @Test
    fun `It should have increment Ticket Number whenever new ticket is created`() {
        val ticketOne =
            Ticket(
                parkingSpot = ParkingSpot(type = ParkingSpotType.TWO_WHEELER, parkingSpotNumber = 1),
                Date()
            )
        val ticketTwo = Ticket(
            parkingSpot = ParkingSpot(type = ParkingSpotType.HEAVY_FOUR_WHEELER, parkingSpotNumber = 2),
            Date()
        )

        assertEquals(TicketNumber(1L), ticketOne.ticketNumber)
        assertEquals(TicketNumber(2L), ticketTwo.ticketNumber)
    }
}