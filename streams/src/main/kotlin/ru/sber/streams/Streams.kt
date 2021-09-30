package ru.sber.streams


// 1. Используя withIndex() посчитать сумму элементов листа, индекс которых кратен 3. (нулевой индекс тоже входит)
fun getSumWithIndexDivisibleByThree(list: List<Long>): Long {
    var sum: Long = 0

    list.withIndex()
        .filter { it.index % 3 == 0 }
        .map { sum += it.value }
    return sum
}

// 2. Используя функцию generateSequence() создать последовательность, возвращающую числа Фибоначчи.
fun generateFibonacciSequence(): Sequence<Int> {
    return generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }
}

// 3. Получить города, в которых есть покупатели.
fun Shop.getCustomersCities(): Set<City> {
    return customers.map { it.city }.toSet()
}

// 4. Получить все когда-либо заказанные продукты.
fun Shop.allOrderedProducts(): Set<Product> {
    val res = customers
        .flatMap { customer ->
            customer.orders
                .flatMap { it.products }
        }.toSet()
    return res
}

// 5. Получить покупателя, который сделал больше всего заказов.
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? {
    return customers.maxByOrNull { customer -> customer.orders.size }
}

// 6. Получить самый дорогой продукт, когда-либо приобртенный покупателем.
fun Customer.getMostExpensiveProduct(): Product? {
    val res = orders.map { order ->
        order.products
            .maxByOrNull { product -> product.price }
    }.maxByOrNull { product -> product!!.price }
    return res
}

// 7. Получить соответствие в мапе: город - количество заказанных и доставленных продуктов в данный город.
fun Shop.getNumberOfDeliveredProductByCity(): Map<City, Int> {
    val cityAndListOfSumsPerCustomer = customers.groupBy(keySelector = { it.city },
        valueTransform = {
            it.orders
                .filter { order -> order.isDelivered }
                .sumOf { order -> order.products.size }
        })
    val res = cityAndListOfSumsPerCustomer.asSequence()
        .associateBy(keySelector = { it.key },
            valueTransform = { it.value.sum() }
        )
    return res
}

// 8. Получить соответствие в мапе: город - самый популярный продукт в городе.
fun Shop.getMostPopularProductInCity(): Map<City, Product> {
    val cityAndListsOfProducts = customers.groupBy(keySelector = { it.city },
        valueTransform = {
            it.orders
                .map { order -> order.products }
        })
    val cityAndMapOfProductAndQuantity = cityAndListsOfProducts.asSequence()
        .associateBy(keySelector = { it.key },
            valueTransform = {
                it.value.flatten().flatten()
                    .groupingBy { product: Product -> product }.eachCount()
            })
    val result = cityAndMapOfProductAndQuantity.asSequence()
        .associateBy(keySelector = { it.key },
            valueTransform = {
                it.value.maxByOrNull { it1 -> it1.value }!!.key
            })
    return result
}

// 9. Получить набор товаров, которые заказывали все покупатели.
fun Shop.getProductsOrderedByAll(): Set<Product> {
    val set = mutableSetOf<Product>()

    val customersAndListsOfProducts = customers
        .associateBy(keySelector = { it },
            valueTransform = { it.orders.map { order -> order.products }.flatten().toSet() })
    customersAndListsOfProducts.values.withIndex()
        .forEach { if (it.index == 0) set.addAll(it.value) else set.retainAll(it.value) }
    return set
}

