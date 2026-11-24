package template;

import io.javelit.core.Jt;
import model.Equacao;
import service.EquacaoService;
import java.util.Map;

public class EquacaoUI {

    public static void main(String[] args) {
        // Título e cabeçalho da aplicação
        Jt.title("Calculadora de Equação do II Grau (Arquitetura 3 Camadas)");
        Jt.markdown("### Modelo: $$y = ax^2 + bx + c$$");
        Jt.markdown("---");

        // Entrada de dados na UI
        double a = Jt.numberInput("Coeficiente 'a':", 1.0);
        double b = Jt.numberInput("Coeficiente 'b':", 0.0);
        double c = Jt.numberInput("Coeficiente 'c':", -4.0);

        if (Jt.button("Calcular Equação")) {
            try {
                // Chamada exclusiva à camada de Serviço (Service)
                Equacao eq = EquacaoService.calcularEquacao(a, b, c);

                Jt.subheader("Resultados:");
                Jt.text("Equação: " + eq.toString());
                Jt.text(String.format("Delta (Δ): %.2f", eq.delta()));

                if (eq.temRaizesReais()) {
                    Jt.success(String.format("x1 = %.4f", eq.x1()));
                    Jt.success(String.format("x2 = %.4f", eq.x2()));
                } else {
                    Jt.warning("A equação não possui raízes reais (Δ < 0).");
                }

                // Geração da tabela através da camada de Serviço
                Jt.markdown("#### Tabela de Pontos da Parábola");
                Map<Double, Double> pontos = EquacaoService.gerarTabelaPontos(eq, -5.0, 5.0, 1.0);

                StringBuilder sb = new StringBuilder();
                sb.append("| X | Y = f(X) |\n|---|---|\n");
                for (Map.Entry<Double, Double> entry : pontos.entrySet()) {
                    sb.append(String.format("| %.1f | %.2f |\n", entry.getKey(), entry.getValue()));
                }
                Jt.markdown(sb.toString());

            } catch (IllegalArgumentException e) {
                Jt.error("[ERRO DE VALIDAÇÃO]: " + e.getMessage());
            } catch (Exception e) {
                Jt.error("[ERRO DE PROCESSAMENTO]: " + e.getMessage());
            }
        }
    }
}
