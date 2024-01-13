package main;

import java.util.Random;
import java.util.Scanner;

public class SurfMangaSimulator1 {

    private static final int TAMANO_TABLERO = 5;
    private static final int NUMERO_MINAS = 5;
    private static char[][] tableroVisible;
    private static char[][] tableroReal;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarTablero();
        colocarMinas();
        jugar();
    }

    private static void inicializarTablero() {
        tableroVisible = new char[TAMANO_TABLERO][TAMANO_TABLERO];
        tableroReal = new char[TAMANO_TABLERO][TAMANO_TABLERO];

        for (int i = 0; i < TAMANO_TABLERO; i++) {
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                tableroVisible[i][j] = '-';
                tableroReal[i][j] = ' ';
            }
        }
    }

    private static void colocarMinas() {
        Random random = new Random();

        for (int i = 0; i < NUMERO_MINAS; i++) {
            int fila = random.nextInt(TAMANO_TABLERO);
            int columna = random.nextInt(TAMANO_TABLERO);

            // Evitar colocar minas en la misma celda
            while (tableroReal[fila][columna] == '*') {
                fila = random.nextInt(TAMANO_TABLERO);
                columna = random.nextInt(TAMANO_TABLERO);
            }

            tableroReal[fila][columna] = '*';
        }
    }

    private static void mostrarTablero(char[][] tablero) {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void jugar() {
        int minasRestantes = NUMERO_MINAS;

        while (minasRestantes > 0) {
            System.out.println("Tablero Actual:");
            mostrarTablero(tableroVisible);

            System.out.print("Ingrese la fila (0-" + (TAMANO_TABLERO - 1) + "): ");
            int fila = scanner.nextInt();

            System.out.print("Ingrese la columna (0-" + (TAMANO_TABLERO - 1) + "): ");
            int columna = scanner.nextInt();

            if (fila < 0 || fila >= TAMANO_TABLERO || columna < 0 || columna >= TAMANO_TABLERO) {
                System.out.println("Entrada inválida. Inténtelo de nuevo.");
                continue;
            }

            if (tableroVisible[fila][columna] != '-') {
                System.out.println("Esta celda ya ha sido seleccionada. Inténtelo de nuevo.");
                continue;
            }

            if (tableroReal[fila][columna] == '*') {
                System.out.println("¡Has golpeado una mina! Fin del juego.");
                tableroVisible[fila][columna] = '*';
                mostrarTablero(tableroVisible);
                return;
            } else {
                // Contar minas cercanas
                int minasCercanas = contarMinasCercanas(fila, columna);
                tableroVisible[fila][columna] = (char) (minasCercanas + '0');

                if (minasCercanas == 0) {
                    // Si no hay minas cercanas, revelar celdas adyacentes
                    revelarCeldasAdyacentes(fila, columna);
                }
            }

            minasRestantes--;
        }

        System.out.println("¡Felicidades! Has ganado el juego.");
    }

    private static int contarMinasCercanas(int fila, int columna) {
        int minasCercanas = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;

                if (nuevaFila >= 0 && nuevaFila < TAMANO_TABLERO &&
                        nuevaColumna >= 0 && nuevaColumna < TAMANO_TABLERO &&
                        tableroReal[nuevaFila][nuevaColumna] == '*') {
                    minasCercanas++;
                }
            }
        }

        return minasCercanas;
    }

    private static void revelarCeldasAdyacentes(int fila, int columna) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;

                if (nuevaFila >= 0 && nuevaFila < TAMANO_TABLERO &&
                        nuevaColumna >= 0 && nuevaColumna < TAMANO_TABLERO &&
                        tableroVisible[nuevaFila][nuevaColumna] == '-') {
                    int minasCercanas = contarMinasCercanas(nuevaFila, nuevaColumna);
                    tableroVisible[nuevaFila][nuevaColumna] = (char) (minasCercanas + '0');

                    if (minasCercanas == 0) {
                        revelarCeldasAdyacentes(nuevaFila, nuevaColumna);
                    }
                }
            }
        }
    }
}


