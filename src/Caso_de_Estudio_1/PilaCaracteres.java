package Caso_de_Estudio_1;

import java.util.ArrayList;

public class PilaCaracteres {
    // Atributos
    private final ArrayList<Character> pilaCaracteres;

    // -- Métodos --

    // Constructor
    // Se utiliza una pila dinámica, debido a que una expresión puede tener una longitud indeterminada.
    public PilaCaracteres() {
        pilaCaracteres = new ArrayList<>();
    }

    public void push(char caracter) {
        pilaCaracteres.add(caracter);
    }

    public Character pop() {
        if(pilaCaracteres.isEmpty()) {
            System.out.println("LA PILA ESTÁ VACÍA");
            return null;
        }
        return pilaCaracteres.removeLast();
    }

    public Character peek() {
        if(pilaCaracteres.isEmpty()) {
            System.out.println("LA PILA ESTÁ VACÍA");
            return null;
        }
        return pilaCaracteres.getLast();
    }

    public boolean isEmpty() {
        return pilaCaracteres.isEmpty();
    }
}
