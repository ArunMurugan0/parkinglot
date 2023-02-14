package parkinglot
import java.util.*

data class Receipt(val ticket: Ticket, val exitDateTime: Date, val fee: Long)