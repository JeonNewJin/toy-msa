package com.uno.catalogservice.domain

data class CatalogInfo(
    val productId: String,
    val productName: String,
    val stock: Int,
    val unitPrice: Int,
) {
    companion object {
        fun from(catalog: Catalog): CatalogInfo =
            CatalogInfo(
                productId = catalog.productId,
                productName = catalog.productName,
                stock = catalog.stock,
                unitPrice = catalog.unitPrice,
            )
    }
}
