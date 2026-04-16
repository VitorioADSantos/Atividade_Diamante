import java.util.*;
import java.util.stream.Collectors;

public class SofaScoreBrasil {

    static class Time {
        String nome;
        int pontos, vitorias, empates, derrotas, golsPro, golsContra;

        public Time(String nome) { this.nome = nome; }
        public int getSaldoGols() { return golsPro - golsContra; }
        public int getPontos() { return pontos; }
        public int getGolsPro() { return golsPro; }
        public String getNome() { return nome; }
    }

    public static void main(String[] args) {
        String[] partidas = {
            "Flamengo:3:1: Palmeiras",
            "Corinthians:0:0:São Paulo",
            "Atletico-MG:2:2:Fluminense",
            "Palmeiras: 1:0: Corinthians",
            "São Paulo:3:2: Flamengo",
            "Fluminense:0:1: Atletico-MG",
            "Flamengo:2:0: Corinthians",
            "Palmeiras:4:1: Fluminense",
            "São Paulo:0:0: Atletico-MG",
            "Corinthians:1:3: Fluminense",
            "Atletico-MG:0:2:Flamengo",
            "Fluminense:1:1:São Paulo"
        };

        Map<String, Time> times = new HashMap<>();
        List<String[]> partidasValidas = new ArrayList<>();


        for (int i = 0; i < partidas.length; i++) {
            String[] dados = parsearPartida(partidas[i]);
            
            if (dados != null) {
                partidasValidas.add(dados);
            }
        }


        int index = 0;
        while (index < partidasValidas.size()) {
            String[] dados = partidasValidas.get(index);
            String nomeCasa = dados[0];
            int golsCasa = Integer.parseInt(dados[1]);
            int golsFora = Integer.parseInt(dados[2]);
            String nomeFora = dados[3];

            times.putIfAbsent(nomeCasa, new Time(nomeCasa));
            times.putIfAbsent(nomeFora, new Time(nomeFora));

            Time timeCasa = times.get(nomeCasa);
            Time timeFora = times.get(nomeFora);

            timeCasa.golsPro += golsCasa;
            timeCasa.golsContra += golsFora;
            timeFora.golsPro += golsFora;
            timeFora.golsContra += golsCasa;

            if (golsCasa > golsFora) {
                timeCasa.pontos += 3;
                timeCasa.vitorias += 1;
                timeFora.derrotas += 1;
            } else if (golsCasa < golsFora) {
                timeFora.pontos += 3;
                timeFora.vitorias += 1;
                timeCasa.derrotas += 1;
            } else {
                timeCasa.pontos += 1;
                timeFora.pontos += 1;
                timeCasa.empates += 1;
                timeFora.empates += 1;
            }
            
            index++; 
        }


        System.out.println("=== ANÁLISE COM STREAMS ===");
        
        Time maisGols = times.values().stream()
            .max(Comparator.comparingInt(Time::getGolsPro))
            .orElse(null);
        System.out.println("- Mais gols marcados: " + (maisGols != null ? maisGols.getNome() : ""));

        double mediaGols = partidasValidas.stream()
            .mapToInt(p -> Integer.parseInt(p[1]) + Integer.parseInt(p[2]))
            .average()
            .orElse(0.0);
        System.out.printf("- Média de gols por partida: %.2f\n", mediaGols);

        List<String> rebaixados = times.values().stream()
            .filter(t -> t.getPontos() < 4)
            .map(Time::getNome)
            .collect(Collectors.toList());
        System.out.println("- Zona de rebaixamento: " + rebaixados + "\n");


        System.out.println("=== CAMPEONATO BRASILEIRO 2026 ===");
        
        System.out.println(String.format("%-3s | %-13s | %-3s | %-3s | %-3s | %-3s | %-4s | %s", 
            "POS", "TIME", "PTS", "V", "E", "D", "SG", "CATEGORIA"));
        
        int[] posicao = {1};

        
        times.values().stream()
            .sorted(Comparator.comparingInt(Time::getPontos).reversed()
                .thenComparingInt(Time::getSaldoGols).reversed())
            .forEach(t -> {
               
                String saldo = t.getSaldoGols() > 0 ? "+" + t.getSaldoGols() : String.valueOf(t.getSaldoGols());
                
                String categoria = definirCategoria(t.getPontos());
                
                System.out.println(String.format("%-3d | %-13s | %-3d | %-3d | %-3d | %-3d | %-4s | %s",
                    posicao[0]++, t.getNome(), t.getPontos(), t.vitorias, t.empates, t.derrotas, saldo, categoria));
            });
    }

    public static String[] parsearPartida(String linha) {
        
        String[] partes = linha.split(":");
        
        if (partes.length != 4) {
            System.out.println("[ERRO] Linha inválida: " + linha);
            return null;
        }

        String timeCasa = partes[0].trim().toUpperCase();
        String golsCasa = partes[1].trim();
        String golsFora = partes[2].trim();
        String timeFora = partes[3].trim().toUpperCase();

        if (timeCasa.isEmpty() || timeFora.isEmpty() || golsCasa.isEmpty() || golsFora.isEmpty()) {
            System.out.println("[ERRO] Linha inválida: " + linha);
            return null;
        }

        return new String[]{timeCasa, golsCasa, golsFora, timeFora};
    }

    public static String definirCategoria(int pontos) {
        
        int faixa;
        if (pontos >= 20) faixa = 1;
        else if (pontos >= 14) faixa = 2;
        else if (pontos >= 8) faixa = 3;
        else if (pontos >= 4) faixa = 4;
        else faixa = 5;

        switch (faixa) {
            case 1: return "LÍDER";
            case 2: return "G4";
            case 3: return "MEIO DE TABELA";
            case 4: return "ALERTA";
            case 5: return "REBAIXAMENTO";
            default: return "";
        }
    }
}