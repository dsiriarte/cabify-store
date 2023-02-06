package com.davidsantiagoiriarte.domain

import com.davidsantiagoiriarte.domain.factory.discount.TShirtDiscountFactory
import com.davidsantiagoiriarte.domain.factory.discount.VoucherDiscountFactory
import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DiscountFactoryUnitTest {

    @Test
    fun `Given TShirtDiscountFactory when doesDiscountApplyForProduct is called should return true if meet the requirements`() {
        val product = Product("TSHIRT", "T shirt test", 20.0, 3)
        val factory = TShirtDiscountFactory()
        assert(factory.doesDiscountApplyForProduct(product))
    }

    @Test
    fun `Given TShirtDiscountFactory when doesDiscountApplyForProduct is called should return false if dont meet the requirements`() {
        val product1 = Product("TSHIRT", "T shirt test", 20.0, 1)
        val product2 = Product("VOUCHER", "Voucher test", 20.0, 4)
        val factory = TShirtDiscountFactory()
        assertFalse(factory.doesDiscountApplyForProduct(product1))
        assertFalse(factory.doesDiscountApplyForProduct(product2))
    }

    @Test
    fun `Given TShirtDiscountFactory when calculateDiscount is called should return the correct discount`() {
        val product1 = Product("TSHIRT", "T shirt test", 20.0, 3)
        val factory = TShirtDiscountFactory()
        val expectedDiscount = Discount.TShirtDiscount(3.0)
        val actualDiscount = factory.calculateDiscount(product1)
        assertEquals(expectedDiscount.description, actualDiscount.description)
        assert(expectedDiscount.discount == actualDiscount.discount)
    }


    @Test
    fun `Given VoucherDiscountFactory when doesDiscountApplyForProduct is called should return true if meet the requirements`() {
        val product = Product("VOUCHER", "Voucher test", 5.0, 2)
        val factory = VoucherDiscountFactory()
        assert(factory.doesDiscountApplyForProduct(product))
    }

    @Test
    fun `Given VoucherDiscountFactory when doesDiscountApplyForProduct is called should return false if dont meet the requirements`() {
        val product1 = Product("TSHIRT", "T shirt test", 20.0, 1)
        val product2 = Product("VOUCHER", "Voucher test", 5.0, 1)
        val factory = VoucherDiscountFactory()
        assertFalse(factory.doesDiscountApplyForProduct(product1))
        assertFalse(factory.doesDiscountApplyForProduct(product2))
    }

    @Test
    fun `Given VoucherDiscountFactory when calculateDiscount is called should return the correct discount`() {
        val product1 = Product("VOUCHER", "Voucher test", 5.0, 2)
        val product2 = Product("VOUCHER", "Voucher test", 5.0, 3)
        val factory = VoucherDiscountFactory()
        val expectedDiscount = Discount.VoucherDiscount(5.0)
        val actualDiscount1 = factory.calculateDiscount(product1)
        val actualDiscount2 = factory.calculateDiscount(product2)
        assertEquals(expectedDiscount.description, actualDiscount1.description)
        assert(expectedDiscount.discount == actualDiscount1.discount)
        assertEquals(expectedDiscount.description, actualDiscount2.description)
        assert(expectedDiscount.discount == actualDiscount2.discount)
    }

}
