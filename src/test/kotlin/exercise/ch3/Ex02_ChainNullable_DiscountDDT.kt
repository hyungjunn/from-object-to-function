package exercise.ch3

import com.ubertob.pesticide.core.DDT
import com.ubertob.pesticide.core.DomainDrivenTest
import exercise.ch3.Item.carrot
import exercise.ch3.Item.milk

class Ex02_ChainNullable_DiscountDDT : DomainDrivenTest<CashierActions>(allActions) {
    val alice by NamedActor(::CustomerActor)

    @DDT
    fun `customer can buy 3x2 items`() = ddtScenario {
        val prices = mapOf(carrot to 2.0, milk to 5.0)
        setUp {
            setupPrices(prices)
            setup3x2(carrot)
        }.thenPlay(
            alice.`can add #qty #item`(3, carrot),
            alice.`can add #qty #item`(1, milk),
            alice.`check total is #total`(9.0)
        )
    }
}
