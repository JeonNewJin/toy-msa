package com.uno.domain.order

interface CatalogServiceClient {
    fun reserveStock(request: Request.ReserveStock)

    class Request {
        data class ReserveStock(
            val productId: String,
            val quantity: Int,
        ) {
            companion object {
                fun from(command: OrderCommand.Create): ReserveStock =
                    ReserveStock(
                        productId = command.productId,
                        quantity = command.quantity,
                    )
            }
        }
    }
}
