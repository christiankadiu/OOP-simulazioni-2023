package a02a.e1;

import java.util.HashMap;
import java.util.Map;

public class DietFactoryImpl implements DietFactory{

    @Override
    public Diet standard() {
        return new Diet() {

            Map<String, Integer> mappa = new HashMap<>();
            
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                mappa.put(name, nutritionMap.get(Nutrient.CARBS)+nutritionMap.get(Nutrient.PROTEINS)+nutritionMap.get(Nutrient.FAT));
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                double somma = 0;
                for (Map.Entry<String, Double> entry : dietMap.entrySet()) {
                    somma = somma + (entry.getValue()*(mappa.get(entry.getKey())))/100;
                }
                if (somma > 1500 && somma < 2000){
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public Diet lowCarb() {
        return new Diet() {
            Map<String, Integer> mappa = new HashMap<>();
            Map<String, Integer> carbs = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                mappa.put(name, nutritionMap.get(Nutrient.CARBS)+nutritionMap.get(Nutrient.PROTEINS)+nutritionMap.get(Nutrient.FAT));
                carbs.put(name, nutritionMap.get(Nutrient.CARBS));
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                double somma = 0;
                double totCarbs = 0;
                for (Map.Entry<String, Double> entry : dietMap.entrySet()) {
                    somma = somma + (entry.getValue()*(mappa.get(entry.getKey())))/100;
                    totCarbs = totCarbs + (entry.getValue()*(carbs.get(entry.getKey())))/100;
                }
                if (somma >= 1000 && somma <= 1500 && totCarbs <= 300){
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public Diet highProtein() {
        return new Diet() {
            Map<String, Integer> mappa = new HashMap<>();
            Map<String, Integer> carbs = new HashMap<>();
            Map<String, Integer> prots = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                mappa.put(name, nutritionMap.get(Nutrient.CARBS)+nutritionMap.get(Nutrient.PROTEINS)+nutritionMap.get(Nutrient.FAT));
                carbs.put(name, nutritionMap.get(Nutrient.CARBS));
                prots.put(name, nutritionMap.get(Nutrient.PROTEINS));
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                double somma = 0;
                double totCarbs = 0;
                double proteine = 0;
                for (Map.Entry<String, Double> entry : dietMap.entrySet()) {
                    somma = somma + (entry.getValue()*(mappa.get(entry.getKey())))/100;
                    totCarbs = totCarbs + (entry.getValue()*(carbs.get(entry.getKey())))/100;
                    proteine = proteine + (entry.getValue()*(prots.get(entry.getKey())))/100;
                }
                if (somma >= 2000 && somma <= 2500 && totCarbs <= 300 && proteine >= 1300){
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public Diet balanced() {
        return new Diet() {
            Map<String, Integer> mappa = new HashMap<>();
            Map<String, Integer> carbs = new HashMap<>();
            Map<String, Integer> prots = new HashMap<>();
            Map<String, Integer> fats = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                mappa.put(name, nutritionMap.get(Nutrient.CARBS)+nutritionMap.get(Nutrient.PROTEINS)+nutritionMap.get(Nutrient.FAT));
                carbs.put(name, nutritionMap.get(Nutrient.CARBS));
                prots.put(name, nutritionMap.get(Nutrient.PROTEINS));
                fats.put(name, nutritionMap.get(Nutrient.FAT));
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                double somma = 0;
                double totCarbs = 0;
                double proteine = 0;
                double fat = 0;
                for (Map.Entry<String, Double> entry : dietMap.entrySet()) {
                    somma = somma + (entry.getValue()*(mappa.get(entry.getKey())))/100;
                    totCarbs = totCarbs + (entry.getValue()*(carbs.get(entry.getKey())))/100;
                    proteine = proteine + (entry.getValue()*(prots.get(entry.getKey())))/100;
                    fat = fat + (entry.getValue()*(fats.get(entry.getKey())))/100;
                }
                if (somma >= 1600 && somma <= 2000 && totCarbs >= 300 && proteine >= 600 && fat >= 400 && fat + proteine <= 1100){
                    return true;
                }
                return false;
            }
            
        };
    }
    
}
