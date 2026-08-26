package com.clemente.lab02carritokotlin

class Carrito(
    val nombreCliente: String
) {

    private val productos = mutableListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
        println("Producto agregado: ${producto.nombre}")
    }

    fun buscarProducto(nombre: String): Producto? {
        return productos.find { it.nombre == nombre }
    }

    fun eliminarProducto(nombre: String): Producto? {
        val productoEncontrado = buscarProducto(nombre)

        if (productoEncontrado == null) {
            return null
        }

        productos.remove(productoEncontrado)

        return productoEncontrado
    }

    fun calcularSubtotal(): Double {
        var subtotal = 0.0

        for (producto in productos) {
            subtotal += producto.precio * producto.cantidad
        }

        return subtotal
    }

    fun calcularIGV(): Double {
        return calcularSubtotal() * 0.18
    }

    fun calcularTotal(): Double {
        return calcularSubtotal() + calcularIGV()
    }

    fun calcularDescuento(): Double {
        val total = calcularTotal()

        return when {
            total > 5000 -> total * 0.10
            total > 3000 -> total * 0.05
            else -> 0.0
        }
    }

    fun calcularTotalConDescuento(): Double {
        return calcularTotal() - calcularDescuento()
    }

    fun cantidadProductos(): Int {
        return productos.size
    }

    fun productoMasCaro(): Producto? {
        return productos.maxByOrNull { it.precio }
    }

    fun mostrarDetalle() {
        println("--------- DETALLE DEL CARRITO ---------")

        var i = 1

        for (producto in productos) {
            val importe = producto.precio * producto.cantidad

            println(
                String.format(
                    "%d. %-20s x%d S/ %8.2f",
                    i,
                    producto.nombre,
                    producto.cantidad,
                    importe
                )
            )

            i++
        }

        println("---------------------------------------")
    }
}