import java.sql.Time;

public class sofa_score_brasil {
    public static void main(String[] args) {
        String[] partidas = {
                "Flamengo:3:1:Palmeiras",
                "Corinthians:0:0:São Paulo",
                "Atletico-MG:2:2:Fluminense",
                "Palmeiras:1:0:Corinthians",
                "São Paulo:3:2:Flamengo",
                "Fluminense:0:1:Atletico-MG",
                "Flamengo:2:0:Corinthians",
                "Palmeiras:4:1:Fluminense",
                "São Paulo:0:0:Atletico-MG",
                "Corinthians:1:3:Fluminense",
                "Atletico-MG:0:2:Flamengo",
                "Fluminense:1:1:São Paulo"
        };

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
}
