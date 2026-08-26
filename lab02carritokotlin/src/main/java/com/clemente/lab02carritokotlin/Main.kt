package com.clemente.lab02carritokotlin

fun main() {

    println("=========================================")
    println(" CARRITO DE COMPRAS - TIENDA TECSUP ")
    println("=========================================")

    val carrito = Carrito("Clemente")

    println("Cliente: ${carrito.nombreCliente}")
    println()

    carrito.agregarProducto(
        Producto("Laptop HP", 2500.0, 1)
    )

    carrito.agregarProducto(
        Producto("Mouse Logitech", 45.5, 2)
    )

    carrito.agregarProducto(
        Producto("Teclado mecanico", 50.8, 4)
    )

    carrito.agregarProducto(
        Producto("Telefono", 130.6, 6)
    )

    println()

    carrito.mostrarDetalle()

    println("Cantidad de productos: ${carrito.cantidadProductos()}")

    val subtotal = carrito.calcularSubtotal()
    val igv = carrito.calcularIGV()
    val total = carrito.calcularTotal()

    println(String.format("%-20s S/ %8.2f", "Subtotal:", subtotal))
    println(String.format("%-20s S/ %8.2f", "IGV (18%):", igv))
    println(String.format("%-20s S/ %8.2f", "TOTAL A PAGAR:", total))

    val masCaro = carrito.productoMasCaro()

    if (masCaro != null) {
        println(
            "Producto mas caro: ${masCaro.nombre} " +
                    String.format("(S/ %.2f)", masCaro.precio)
        )
    }

    val descuento = carrito.calcularDescuento()

    if (descuento > 0) {
        println(
            String.format(
                "Descuento aplicado: S/ %.2f",
                descuento
            )
        )
    } else {
        println("Descuento aplicado: No aplica")
    }

    println(
        String.format(
            "TOTAL CON DESCUENTO: %.2f",
            carrito.calcularTotalConDescuento()
        )
    )

    println()

    println(
        "Producto encontrado: ${
            carrito.buscarProducto("Laptop HP")?.nombre
        }"
    )

    println(
        "Producto eliminado: ${
            carrito.eliminarProducto("Laptop HP")?.nombre
        }"
    )

    println()
    println("-----------------------------------")
    println("DETALLES ACTUALIZADOS")
    println("-----------------------------------")

    carrito.mostrarDetalle()

    println("Cantidad de productos: ${carrito.cantidadProductos()}")

    println(
        String.format(
            "%-20s S/ %8.2f",
            "Subtotal:",
            carrito.calcularSubtotal()
        )
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "IGV (18%):",
            carrito.calcularIGV()
        )
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "TOTAL A PAGAR:",
            carrito.calcularTotal()
        )
    )
}