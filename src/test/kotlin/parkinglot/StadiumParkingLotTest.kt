package parkinglot

import exceptions.ParkingSpotNotAvailableException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import vehicle.Car
import vehicle.MotorCycle
import vehicle.Vehicle
import java.time.LocalDateTime
import java.util.stream.Stream

class StadiumParkingLotTest {

    companion object {

        @JvmStatic
        fun getFeeCalculationTestCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    LocalDateTime.of(2022, 9, 11, 11, 0),
                    LocalDateTime.of(2022, 9, 11, 14, 40),
                    MotorCycle(),
                    30
                ),
                Arguments.of(
                    LocalDateTime.of(2022, 11, 11, 0, 0),
                    LocalDateTime.of(2022, 11, 11, 14, 59),
                    MotorCycle(),
                    390
                ),
                Arguments.of(
                    LocalDateTime.of(2022, 9, 11, 11, 30),
                    LocalDateTime.of(2022, 9, 11, 23, 0),
                    Car(),
                    180
                ),
                Arguments.of(
                    LocalDateTime.of(2022, 9, 11, 11, 30),
                    LocalDateTime.of(2022, 9, 12, 0, 35),
                    Car(),
                    580
                ),
            )
        }
    }

    @MethodSource("getFeeCalculationTestCases")
    @ParameterizedTest
    fun `it should generate parking fee receipt`(
        entryDateTime: LocalDateTime,
        exitDateTime: LocalDateTime,
        vehicle: Vehicle,
        fee: Long
    ) {
        val config = ParkingSpotCountForEachTypeConfig(
            mapOf(
                VehicleType.TWO_WHEELER to 100,
                VehicleType.LIGHT_FOUR_WHEELER to 40,
            )
        )
        val parkingLot: ParkingLot = StadiumParkingLot(config)

        val ticket = parkingLot.generateParkingTicket(vehicle, entryDateTime)

        val receipt = parkingLot.generateParkingFeeReceipt(ticket.ticketNumber, exitDateTime)

        assertEquals(ticket, receipt.ticket)
        assertEquals(fee, receipt.fee)
    }

    @Test
    fun `it should throw exception if parking spot is unavailable`() {
        val config = ParkingSpotCountForEachTypeConfig(
            mapOf(
                VehicleType.TWO_WHEELER to 3,
                VehicleType.LIGHT_FOUR_WHEELER to 2,
            )
        )
        val parkingLot: ParkingLot = StadiumParkingLot(config)

        parkingLot.generateParkingTicket(MotorCycle(), LocalDateTime.MIN)
        parkingLot.generateParkingTicket(MotorCycle(), LocalDateTime.MIN)
        parkingLot.generateParkingTicket(MotorCycle(), LocalDateTime.MIN)
        parkingLot.generateParkingTicket(Car(), LocalDateTime.MIN)
        parkingLot.generateParkingTicket(Car(), LocalDateTime.MIN)

        assertThrows<ParkingSpotNotAvailableException> {
            parkingLot.generateParkingTicket(MotorCycle(), LocalDateTime.MIN)
        }
        assertThrows<ParkingSpotNotAvailableException> {
            parkingLot.generateParkingTicket(Car(), LocalDateTime.MIN)
        }
    }
}