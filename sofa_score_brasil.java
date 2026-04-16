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
        
            
       


    }
}
