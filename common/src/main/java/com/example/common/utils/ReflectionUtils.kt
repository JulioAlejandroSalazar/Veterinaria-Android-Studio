package com.example.common.utils

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.memberFunctions

object ReflectionUtils {
    fun listarMiembros(kclass: KClass<*>) {
        println("\n=== Miembros de ${kclass.simpleName} ===")
        println("Propiedades:")
        kclass.memberProperties.forEach { println(" - ${it.name}: ${it.returnType}") }
        println("Funciones:")
        kclass.memberFunctions.forEach { println(" - ${it.name}()") }
    }
}
