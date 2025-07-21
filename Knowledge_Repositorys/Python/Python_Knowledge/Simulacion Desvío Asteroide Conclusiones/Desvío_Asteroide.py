import numpy as np
import matplotlib.pyplot as plt
import math
from typing import List, Callable


# Constantes y datos


M = 5.972 * 10 ** 24  # Masa de la Tierra
G = 6.672 * 10 ** -11  # Constante gravitacional
posicion_radial_satelite: int = 1820000000  # Radio inicial del proyectil (en metros)
posicion_radial_meteorito: int = 3085500000  # Posición radial del asteroide (en metros)
posicion_angular_meteorito: float = 2.617993878  # Posición angular del asteroide (en radianes)


# Métodos


# Sistema de ecuaciones diferenciales -> segunda ley de Newton
def ecuacion_fuerzas(estado: int | float):
    posicion_radial, tita, velocidad_radial, velocidad_angular = estado
    aceleracion_radial = posicion_radial * velocidad_angular ** 2 - (G * M) / posicion_radial ** 2
    aceleracion_angular = -2 * velocidad_radial * velocidad_angular / posicion_radial
    return np.array([velocidad_radial, velocidad_angular, aceleracion_radial, aceleracion_angular])


# Método de Runge-Kutta de cuarto orden
def runge_kutta_04(f: Callable, posicion, h: int):
    # posicion define las coordenadas del objeto
    k1 = f(posicion)
    k2 = f(posicion + h * k1 / 2)
    k3 = f(posicion + h * k2 / 2)
    k4 = f(posicion + h * k3)
    return posicion + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4)


# Método de Adams-Bashforth de tercer orden
def adams_bashforth_03(f: Callable, posiciones: List, h: int):
    f1 = f(posiciones[-1])
    f2 = f(posiciones[-2])
    f3 = f(posiciones[-3])
    return posiciones[-1] + h * (23 * f1 - 16 * f2 + 5 * f3) / 12


# Función f(v0) para calcular v0 aproximado
def f(v0: float):
    velocidad_angular_inicial: float = v0 / posicion_radial_satelite
    posicion_inicial = np.array([posicion_radial_satelite, 0.0, 0.0, velocidad_angular_inicial]) # _ArrayType

    # Tiempo de integración
    tiempo_span = (0, 1e9)
    tiempo_eval = np.linspace(*tiempo_span, 5000)
    h: int = 8950  # Paso de tiempo

    # Inicialización con RK4
    posiciones: List = [posicion_inicial] # List[_ArrayType]
    tiempos: List = [tiempo_eval[0]] # List[_ArrayType]
    posiciones_radiales: List = [posicion_inicial[0]]  # Almacenar la posición radial del proyectil
    for i in range(2):
        posiciones.append(runge_kutta_04(ecuacion_fuerzas, posiciones[-1], h))
        tiempos.append(tiempos[-1] + h)
        posiciones_radiales.append(posiciones[-1][0])

    # Iteración con AB3
    for t in tiempo_eval[3:]:
        nuevo_estado = adams_bashforth_03(ecuacion_fuerzas, posiciones, h)
        posiciones.append(nuevo_estado)
        tiempos.append(t)
        posiciones_radiales.append(nuevo_estado[0])

        # Comparación de posiciones angulares
        if nuevo_estado[1] >= posicion_angular_meteorito:
            return nuevo_estado[0] - posicion_radial_meteorito, posiciones_radiales  # Diferencia de posición radial con el asteroide

    return 1e10, posiciones_radiales  # Valor alto si no se alcanza la posición angular del asteroide


# Método de bisección para encontrar la velocidad óptima mediante la menor diferencia entre las posiciones
def biseccion(f: Callable, v_min: float, v_max: float, tolerancia=1e-6, max_iter=500):
    if f(v_min)[0] * f(v_max)[0] >= 0:
        print("No se cumple el criterio de cambio de signo en el intervalo dado.")
        return None

    for i in range(max_iter):
        v_med: float = (v_min + v_max) / 2.0
        f_med: float = f(v_med)[0]

        # Verificar si se alcanzó la tolerancia
        if abs(f_med) < tolerancia:
            print(f"Convergencia alcanzada en {i + 1} iteraciones.")
            return v_med

        # Actualizar el intervalo en función del signo de f(v_med)
        if f(v_min)[0] * f_med < 0:
            v_max = v_med
        else:
            v_min = v_med

    print("No se alcanzó la convergencia.")
    return (v_min + v_max) / 2.0


# Función para graficar la distancia entre el proyectil y el meteorito
def graficar_posicion(v0_optimo: float) -> None:
    # Ejecutar con la velocidad óptima para obtener la trayectoria y graficar
    radial_posiciones_optimo: List = f(v0_optimo)[1]

    # Graficar la trayectoria radial para v0 óptimo
    plt.figure(figsize=(10, 6))  # Nuevo gráfico
    plt.plot(radial_posiciones_optimo, label="Trayectoria del proyectil")
    plt.axhline(y=posicion_radial_meteorito, color='r', linestyle='--', label="Posición del asteroide")
    plt.xlabel("Tiempo (s)")
    plt.ylabel("Posición radial (m)")
    plt.title("Figura 1: Trayectoria radial del proyectil con v0 óptimo")
    plt.legend()
    plt.show()


# Función para graficar las trayectorias de distintos valores de v0
def graficar_v0(v0_values, max_points=2000) -> None:
    plt.figure(figsize=(10, 6))  # Tamaño de la figura
    for v0 in v0_values:
        posiciones_radiales = f(v0)[1]

        # Reducimos el número de puntos a max_points (200 puntos)
        if len(posiciones_radiales) > max_points:
            indices = np.linspace(0, len(posiciones_radiales) - 1, max_points, dtype=int)
            posiciones_reducidas = [posiciones_radiales[i] for i in indices]
        else:
            posiciones_reducidas = posiciones_radiales

        # Trayectoria
        plt.plot(posiciones_reducidas, label=f"v0 = {v0:.2e} m/s")

    # Posición del meteorito
    plt.axhline(y=posicion_radial_meteorito, color='r', linestyle='--', label="Posición del asteroide")
    plt.xlabel("Tiempo (s)")
    plt.ylabel("Posición radial (m)")
    plt.title("Figura 2: Trayectoria radial del proyectil para diferentes v0 (200 iteraciones)")
    plt.legend()
    plt.show()


# Ejecución del código


if __name__ == '__main__':

    # Estimar el intervalo inicial para la bisección
    v0_min: float = math.sqrt(G * M / posicion_radial_satelite) * 0.7
    v0_max: float = math.sqrt(G * M / posicion_radial_satelite) * 1.5
    # np.sqrt() retorna un objeto propio de la librería. math.sqrt() retorna un objeto float

    # Encontrar la velocidad óptima utilizando el método de bisección
    v0_optimo = biseccion(f, v0_min, v0_max)

    # Gráfico de la trayectoria radial del proyectil con v0 óptimo (1er gráfico)
    if v0_optimo is not None:
        print("Velocidad inicial óptima encontrada:", v0_optimo)
        graficar_posicion(v0_optimo)
    else:
        print("No se encontró una solución adecuada.")

    # Graficar las trayectorias para diferentes v0
    v0_values = np.linspace(0.7 * np.sqrt(G * M / posicion_radial_satelite), 1.5 * np.sqrt(G * M / posicion_radial_satelite), 5)
    v0_values = np.append(v0_values, v0_optimo)
    graficar_v0(v0_values)
