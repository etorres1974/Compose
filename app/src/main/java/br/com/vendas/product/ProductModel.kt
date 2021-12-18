package br.com.vendas.product

import java.util.*

interface ProductModel {
    val id: String
    val name: String
    val image : String
}

data class MockProduct(
    override val name: String = "Mock",
    override val id: String = UUID.randomUUID().toString(),
    override val image : String = androidLogo
) : ProductModel

val androidLogo = "https://developer.android.com/images/brand/Android_Robot.png"