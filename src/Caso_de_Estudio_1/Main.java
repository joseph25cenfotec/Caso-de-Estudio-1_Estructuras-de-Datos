package Caso_de_Estudio_1;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una expresión aritmética: ");
        String expresion = scanner.nextLine();

        Token top = construirLista(expresion);
        PilaCaracteres pila = new PilaCaracteres();
        Token actual = top;
        boolean error = false;

        while (actual != null) {

            char caracter = actual.getValor();

            if (caracter == '(') {
                pila.push(caracter);
            } else if (caracter == ')') {
                if (pila.isEmpty()) {
                    System.out.println("ERROR: Paréntesis de cierre sin apertura.");
                    error = true;
                    break;
                } else {
                    pila.pop();
                }
            }
            actual = actual.siguiente;
        }

        if (!error) {
            if (!pila.isEmpty()) {
                System.out.println("ERROR: Hay paréntesis sin cerrar.");
            } else {
                System.out.println("Expresión válida.");
            }
        }

        scanner.close();
    }

    public static Token construirLista(String expresion) {

        if (expresion == null || expresion.isEmpty()) return null;

        Token top = new Token(expresion.charAt(0));
        Token actual = top;

        for (int i = 1; i < expresion.length(); i++) {
            actual.siguiente = new Token(expresion.charAt(i));
            actual = actual.siguiente;
        }

        return top;
    }
}