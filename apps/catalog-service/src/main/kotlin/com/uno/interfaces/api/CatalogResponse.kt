package com.uno.interfaces.api

import com.uno.domain.CatalogInfo

data class CatalogResponse(
    val productId: String,
    val productName: String,
    val stock: Int,
    val unitPrice: Int,
) {
    companion object {
        fun from(info: CatalogInfo): CatalogResponse =
            CatalogResponse(
                productId = info.productId,
                productName = info.productName,
                stock = info.stock,
                unitPrice = info.unitPrice,
            )
    }
}
