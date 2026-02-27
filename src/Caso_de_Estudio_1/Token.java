package Caso_de_Estudio_1;

public class Token {
    private char valor; // el carácter de la expresión: ('+', '*', '5', ...), número o operador
    Token siguiente; // apunta al siguiente nodo en la lista

    public Token(char valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public char getValor() {
        return valor;
    }
}