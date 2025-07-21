import numpy as np
import matplotlib.pyplot as plt
import time
from scipy.sparse import diags, identity, kron
from scipy.sparse.linalg import spsolve

# Construcción matriz A para método implícito
def construir_matriz(nx, ny, dx, dy, dt, alpha):
    Nix, Niy = nx - 2, ny - 2
    Ix = identity(Nix)
    Iy = identity(Niy)

    main_diag_x = 2 * (1/dx**2 + 1/dy**2) * np.ones(Nix)
    off_diag_x = -1/dx**2 * np.ones(Nix - 1)
    Tx = diags([off_diag_x, main_diag_x, off_diag_x], [-1, 0, 1])

    off_diag_y = -1/dy**2 * np.ones(Niy - 1)
    Ty = diags([off_diag_y, off_diag_y], [-1, 1], shape=(Niy, Niy))

    L = kron(Iy, Tx) + kron(Ty, Ix)
    A = identity(Nix*Niy) - dt * alpha * L
    return A

# Inicializar temperatura y condiciones de frontera
def inicializar_T(nx, ny):
    T = np.ones((ny, nx)) * 25
    T[:, 0] = 100    # borde izquierdo
    T[:, -1] = 50    # borde derecho
    T[0, :] = 0      # borde superior
    T[-1, :] = 75    # borde inferior
    # Fuente interna caliente
    T[ny//2 - 1:ny//2 + 2, nx//2 - 1:nx//2 + 2] = 200
    return T

# TODO: Métodos a desarrollar
def optimizado(A, b):
    A = A.toarray()
    n = len(b)
    a = np.zeros(n)
    b_diag = np.zeros(n)
    c = np.zeros(n)

    for i in range(n):
        b_diag[i] = A[i, i]
        if i > 0:
            a[i] = A[i, i - 1]
        if i < n - 1:
            c[i] = A[i, i + 1]

    # Forward sweep
    for i in range(1, n):
        m = a[i] / b_diag[i - 1]
        b_diag[i] -= m * c[i - 1]
        b[i] -= m * b[i - 1]

    # Backward substitution
    x = np.zeros(n)
    x[-1] = b[-1] / b_diag[-1]
    for i in range(n - 2, -1, -1):
        x[i] = (b[i] - c[i] * x[i + 1]) / b_diag[i]

    return x


def gauss_pivoteo(A, b):
    A = A.toarray().astype(float)  # Convertir a array denso
    b = b.copy().astype(float)
    n = len(b)

    for i in range(n):
        # Pivoteo parcial
        max_row = np.argmax(np.abs(A[i:, i])) + i
        if i != max_row:
            A[[i, max_row]] = A[[max_row, i]]
            b[[i, max_row]] = b[[max_row, i]]

        # Eliminación
        for j in range(i + 1, n):
            factor = A[j, i] / A[i, i]
            A[j, i:] -= factor * A[i, i:]
            b[j] -= factor * b[i]

    # Sustitución hacia atrás
    x = np.zeros_like(b)
    for i in range(n - 1, -1, -1):
        x[i] = (b[i] - np.dot(A[i, i + 1:], x[i + 1:])) / A[i, i]
    return x


# Un paso de simulación con el método implícito y método de solución
# optimización tridiagonal solo aplica si A realmente es tridiagonal.
def paso_simulacion(T, A, nx, ny, dx, dy, dt, alpha, metodo_solucion):
    b = T[1:-1, 1:-1].copy()
    # Incorporar condiciones de borde en b
    b[:, 0] += dt * alpha * T[1:-1, 0] / dx**2
    b[:, -1] += dt * alpha * T[1:-1, -1] / dx**2
    b[0, :] += dt * alpha * T[0, 1:-1] / dy**2
    b[-1, :] += dt * alpha * T[-1, 1:-1] / dy**2
    b = b.flatten()

    if metodo_solucion == 'directo':
        T_vec = spsolve(A, b)
    elif metodo_solucion == 'optimizado':
        T_vec = optimizado(A, b)
    elif metodo_solucion == 'gauss_pivoteo':
        T_vec  = gauss_pivoteo(A, b)
    else:
        raise ValueError("Método de solución no reconocido")

    T_new = T.copy()
    T_new[1:-1, 1:-1] = T_vec.reshape((ny - 2, nx - 2))
    # Mantener la fuente de calor interna fija
    T_new[ny//2 - 1:ny//2 + 2, nx//2 - 1:nx//2 + 2] = 200
    return T_new

# Simular múltiples pasos, medir tiempos
def simular(nx, ny, dt, alpha, pasos, metodo_solucion):
    dx = 1.0 / (nx - 1)
    dy = 1.0 / (ny - 1)
    A = construir_matriz(nx, ny, dx, dy, dt, alpha)
    T = inicializar_T(nx, ny)

    tiempos = []
    for _ in range(pasos):
        start = time.time()
        T = paso_simulacion(T, A, nx, ny, dx, dy, dt, alpha, metodo_solucion)
        end = time.time()
        tiempos.append(end - start)

    tiempo_promedio = np.mean(tiempos)
    return T, tiempo_promedio

# Error RMS relativo
def error_rms(T_ref, T):
    return np.sqrt(np.mean((T_ref - T)**2)) / np.sqrt(np.mean(T_ref**2))

# --------- Experimentos ---------

resoluciones = [20, 30, 50, 70, 100, 500]
dt = 0.1
alpha = 0.01
pasos = 10
metodos = ['directo', 'optimizado', 'gauss_pivoteo']

# TODO: correr experimientos, obtener resultados y compararlos

resultados_tiempo = {metodo: [] for metodo in metodos}
resultados_error = {metodo: [] for metodo in metodos if metodo != 'directo'}

for res in resoluciones:
    print(f"Resolución: {res}x{res}")
    T_ref, tiempo_ref = simular(res, res, dt, alpha, pasos, 'directo')
    resultados_tiempo['directo'].append(tiempo_ref)

    for metodo in ['optimizado', 'gauss_pivoteo']:
        T_metodo, tiempo_metodo = simular(res, res, dt, alpha, pasos, metodo)
        err = error_rms(T_ref, T_metodo)

        resultados_tiempo[metodo].append(tiempo_metodo)
        resultados_error[metodo].append(err)

# Graficar resultados


plt.figure()
for metodo in metodos:
    plt.plot(resoluciones, resultados_tiempo[metodo], label=metodo)
plt.xlabel("Tamaño de la cuadrícula (n x n)")
plt.ylabel("Tiempo promedio por paso (s)")
plt.title("Tiempo de ejecución")
plt.legend()
plt.grid(True)
plt.savefig("tiempo_vs_tamano.png")

plt.figure()
for metodo in ['optimizado', 'gauss_pivoteo']:
    plt.plot(resoluciones, resultados_error[metodo], label=metodo)
plt.xlabel("Tamaño de la cuadrícula (n x n)")
plt.ylabel("Error RMS relativo")
plt.title("Error numérico vs tamaño del sistema")
plt.legend()
plt.grid(True)
plt.savefig("error_vs_tamano.png")

plt.show()