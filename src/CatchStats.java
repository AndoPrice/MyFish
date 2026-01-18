public class CatchStats {
    public static Catch longestCatch(Catch[] capturas) {
        Catch max = capturas[0];

        for (int i = 0; i<capturas.length; i++) {
            if (capturas[i].tamano > max.tamano) {
                max = capturas[i];
            }
        }
        return max;
    }

    public static Catch heaviestCatch(Catch[] capturas) {
        Catch max = capturas[0];

        for (int i = 0; i<capturas.length; i++) {
            if (capturas[i].peso > max.peso) {
                max = capturas[i];
            }
        }
        return max;
    }

    public static Object[] commonSpecies(Catch[] capturas){
        Especie[] species = new Especie[capturas.length];
        int[] counts = new int[capturas.length];
        int uniqueCount = 0;

        for (int i = 0; i<capturas.length; i++) {
            Especie e = capturas[i].especie;
            boolean found = false;

            for (int j = 0; j < uniqueCount; j++) {
                if (species[j] == e) {
                    counts[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                species[uniqueCount] = e;
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < uniqueCount; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }

        return new Object[]{species[maxIndex], counts[maxIndex]};
    }
    public static float averageWeight(Catch[] capturas) {
        float sum = 0;
        for (int i = 0; i<capturas.length; i++){
            sum += capturas[i].peso;
        }
        if(capturas.length > 0){
            return sum / capturas.length;
        }
        else{
            return 0;
        }
    }

    public static float averageLength(Catch[] capturas) {
        float sum = 0;
        for (int i = 0; i<capturas.length; i++){
            sum += capturas[i].tamano;
        }
        if(capturas.length > 0){
            return sum / capturas.length;
        }
        else{
            return 0;
        }
    }

}

