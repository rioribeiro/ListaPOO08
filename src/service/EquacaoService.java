package service;

import model.Equacao;
import java.util.LinkedHashMap;
import java.util.Map;

public class EquacaoService {

    // Método para calcular a equação e retornar um objeto processado
    public static Equacao calcularEquacao(double a, double b, double c) {
        return new Equacao(a, b, c);
    }

    // Método que gera a tabela de pontos f(x) para um intervalo de X
    public static Map<Double, Double> gerarTabelaPontos(Equacao eq, double inicio, double fim, double passo) {
        Map<Double, Double> pontos = new LinkedHashMap<>();
        for (double x = inicio; x <= fim; x += passo) {
            pontos.put(x, eq.y(x));
        }
        return pontos;
    }
}
