package template;

import io.javelit.core.Jt;
import io.javelit.core.Server;
import model.Equacao;
import service.EquacaoService;
import java.util.Map;

public class EquacaoUI {

    public static void main(String[] args) {
        Server.builder(EquacaoUI::render, 8080).build().start();
    }

    private static void render() {
        Jt.title("Calculadora de Equação do II Grau").use();
        Jt.markdown("### Modelo: $$y = ax^2 + bx + c$$").use();
        Jt.markdown("---").use();

        Double a = Jt.numberInput("Coeficiente 'a':", Double.class).value(1.0).use();
        Double b = Jt.numberInput("Coeficiente 'b':", Double.class).value(0.0).use();
        Double c = Jt.numberInput("Coeficiente 'c':", Double.class).value(-4.0).use();

        if (Jt.button("Calcular Equação").use()) {
            if (a == null || b == null || c == null) {
                Jt.error("Preencha todos os coeficientes.").use();
                return;
            }
            try {
                Equacao eq = EquacaoService.calcularEquacao(a, b, c);

                Jt.subheader("Resultados:").use();
                Jt.text("Equação: " + eq).use();
                Jt.text(String.format("Delta (Δ): %.2f", eq.delta())).use();

                if (eq.temRaizesReais()) {
                    Jt.success(String.format("x1 = %.2f", eq.x1())).use();
                    Jt.success(String.format("x2 = %.2f", eq.x2())).use();
                } else {
                    Jt.warning("A equação não possui raízes reais (Δ < 0).").use();
                }

                Jt.markdown("#### Tabela de Pontos da Parábola").use();
                Map<Double, Double> pontos = EquacaoService.gerarTabelaPontos(eq, -5.0, 5.0, 1.0);

                StringBuilder sb = new StringBuilder();
                sb.append("| X | Y = f(X) |\n|---|---|\n");
                for (Map.Entry<Double, Double> entry : pontos.entrySet()) {
                    sb.append(String.format("| %.1f | %.2f |\n", entry.getKey(), entry.getValue()));
                }
                Jt.markdown(sb.toString()).use();

            } catch (IllegalArgumentException e) {
                Jt.error("[ERRO DE VALIDAÇÃO]: " + e.getMessage()).use();
            } catch (Exception e) {
                Jt.error("[ERRO DE PROCESSAMENTO]: " + e.getMessage()).use();
            }
        }
    }
}
