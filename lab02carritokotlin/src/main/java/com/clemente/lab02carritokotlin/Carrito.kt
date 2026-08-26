package com.clemente.lab02carritokotlin

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

fun calcularSubtotal(productos: List<Producto>): Double {
    var subtotal = 0.0

    for (p in productos) {
        subtotal += p.precio * p.cantidad
    }

    return subtotal
}

fun calcularIGV(subtotal: Double): Double {
    return subtotal * 0.18
}

fun calcularTotal(subtotal: Double, igv: Double): Double {
    return subtotal + igv
}

fun calcularDescuento(total: Double): Double {
    return when {
        total > 5000 -> total * 0.10
        total > 3000 -> total * 0.05
        else -> 0.0
    }
}

fun buscarProducto(productos: List<Producto>, nombre: String): Producto? {
    return productos.find {
        it.nombre == nombre
    }
}

fun eliminarProducto(
    productos: MutableList<Producto>,
    nombre: String
): Producto? {

    val productoEncontrado = productos.find {
        it.nombre == nombre
    }

    if (productoEncontrado == null) {
        return null
    }

    productos.removeIf {
        it.nombre == nombre
    }

    return productoEncontrado
}

fun mostrarDetalle(productos: List<Producto>) {

    println("--------- DETALLE DEL CARRITO ---------")

    var i = 1

    for (p in productos) {

        val importe = p.precio * p.cantidad

        println(
            String.format(
                "%d. %-20s x%d S/ %8.2f",
                i,
                p.nombre,
                p.cantidad,
                importe
            )
        )

        i++
    }

    println("---------------------------------------")
}

fun main() {

    println("=========================================")
    println(" CARRITO DE COMPRAS - TIENDA TECSUP ")
    println("=========================================")

    // INGRESAR NOMBRE DEL CLIENTE

    print("Ingrese el nombre del cliente: ")
    val nombreCliente = readln()

    val carrito = mutableListOf<Producto>()

    println()
    println("Cliente: $nombreCliente")

    // INGRESAR CANTIDAD DE PRODUCTOS

    println()
    print("¿Cuántos productos desea agregar?: ")
    val cantidadProductos = readln().toInt()

    println()

    // INGRESAR LOS PRODUCTOS

    for (i in 1..cantidadProductos) {

        println("Producto $i")
        println("-----------------------")

        print("Nombre: ")
        val nombre = readln()

        print("Precio: S/ ")
        val precio = readln().toDouble()

        print("Cantidad: ")
        val cantidad = readln().toInt()

        val producto = Producto(
            nombre,
            precio,
            cantidad
        )

        carrito.add(producto)

        println("Producto agregado: ${producto.nombre}")
        println()
    }

    // MOSTRAR CARRITO

    mostrarDetalle(carrito)

    println("Cantidad de productos diferentes: ${carrito.size}")

    println()

    // CALCULAR TOTALES

    val subtotal = calcularSubtotal(carrito)
    val igv = calcularIGV(subtotal)
    val total = calcularTotal(subtotal, igv)

    println(
        String.format(
            "%-20s S/ %8.2f",
            "Subtotal:",
            subtotal
        )
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "IGV (18%):",
            igv
        )
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "TOTAL A PAGAR:",
            total
        )
    )

    println("---------------------------------------")

    // PRODUCTO MÁS CARO

    val masCaro = carrito.maxByOrNull {
        it.precio
    }

    if (masCaro != null) {

        println(
            "Producto mas caro: ${masCaro.nombre} " +
                    String.format("(S/ %.2f)", masCaro.precio)
        )
    }

    // CALCULAR DESCUENTO

    val descuento = calcularDescuento(total)

    when {
        total > 5000 -> {
            println(
                "Descuento aplicado: 10% por compra mayor a S/ 5000"
            )
        }

        total > 3000 -> {
            println(
                "Descuento aplicado: 5% por compra mayor a S/ 3000"
            )
        }

        else -> {
            println("Descuento aplicado: No aplica")
        }
    }

    val totalConDescuento = total - descuento

    println(
        String.format(
            "TOTAL CON DESCUENTO: S/ %.2f",
            totalConDescuento
        )
    )

    // BUSCAR PRODUCTO

    println()
    println("---------------------------------------")
    println("BUSCAR PRODUCTO")
    println("---------------------------------------")

    print("Ingrese el nombre del producto a buscar: ")
    val nombreBuscar = readln()

    val productoBuscado = buscarProducto(
        carrito,
        nombreBuscar
    )

    if (productoBuscado != null) {

        println(
            "Producto encontrado: ${productoBuscado.nombre}"
        )

        println(
            "Precio: S/ ${productoBuscado.precio}"
        )

        println(
            "Cantidad: ${productoBuscado.cantidad}"
        )

    } else {

        println("Producto no encontrado")
    }

    // ELIMINAR PRODUCTO

    println()
    println("---------------------------------------")
    println("ELIMINAR PRODUCTO")
    println("---------------------------------------")

    print("Ingrese el nombre del producto a eliminar: ")
    val nombreEliminar = readln()

    val productoEliminado = eliminarProducto(
        carrito,
        nombreEliminar
    )

    if (productoEliminado != null) {

        println(
            "Producto eliminado: ${productoEliminado.nombre}"
        )

    } else {

        println("Producto no encontrado")
    }

    // MOSTRAR CARRITO ACTUALIZADO

    println()
    println("---------------------------------------")
    println("DETALLES ACTUALIZADOS")
    println("---------------------------------------")

    mostrarDetalle(carrito)

    println(
        "Cantidad de productos diferentes: ${carrito.size}"
    )

    println()

    val nuevoSubtotal = calcularSubtotal(carrito)
    val nuevoIgv = calcularIGV(nuevoSubtotal)
    val nuevoTotal = calcularTotal(
        nuevoSubtotal,
        nuevoIgv
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "Subtotal:",
            nuevoSubtotal
        )
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "IGV (18%):",
            nuevoIgv
        )
    )

    println(
        String.format(
            "%-20s S/ %8.2f",
            "TOTAL A PAGAR:",
            nuevoTotal
        )
    )

    println("---------------------------------------")

    println()
    println("Gracias por su compra, $nombreCliente!")
}