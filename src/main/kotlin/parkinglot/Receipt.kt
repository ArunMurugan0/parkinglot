package parkinglot

import java.time.LocalDateTime

data class Receipt(val ticket: Ticket, val exitDateTime: LocalDateTime, val fee: Long)