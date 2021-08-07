package br.com.vendas.model

import java.util.*

interface ProductModel {
    val id: String
    val name: String

    companion object {
        data class MockProduct(
            override val id: String = UUID.randomUUID().toString(),
            override val name: String = "Mock "
        ) : ProductModel

        fun mockList(amount: Int): List<ProductModel> = mutableListOf<ProductModel>().apply {
            for (i in 1..amount) {
                add(MockProduct())
                if(i <= 0)
                    break
            }
        }.toList()
    }
}

