package a01a.e1;

import java.util.Set;
import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiFunction;

public class TimeableFactoryImpl implements TimetableFactory{


    @Override
    public Timetable empty() {
        return new Timetable() {

            Map<String, Map<String, Integer>> calendar = new HashMap<>();  
                                                                      
            @Override
            public Timetable addHour(String activity, String day) {
                calendar.put(day, new HashMap<>());
                calendar.get(day).put(activity, 1);
                return this;
            }

            @Override
            public Set<String> activities() {
                Set<String> set = new HashSet<>();
                for (Map.Entry<String, Map<String, Integer>> entry : calendar.entrySet()) {
                    set.addAll(entry.getValue().keySet());
                }
                return set;
            }

            @Override
            public Set<String> days() {
               return calendar.keySet();
            }

            @Override
            public int getSingleData(String activity, String day) {
                if (calendar.keySet().contains(day)){
                    if (calendar.get(day).keySet().contains(activity)){
                        return calendar.get(day).get(activity);
                    }
                }
                return 0;
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                int somma = 0;
                for (String day : days) {
                    if (calendar.keySet().contains(day)){
                        for (Map.Entry<String, Integer> entry : calendar.get(day).entrySet()) {
                            somma = somma + entry.getValue();
                        }
                    }      
                }
                return somma;    
            }
            
        };
    }

    @Override
    public Timetable single(String activity, String day) {
   
        Map<String, Map<String, Integer>> calendar = new HashMap<>();
        calendar.put(day, new HashMap<String, Integer>());
        calendar.get(day).put(activity, 1);
        return new Timetable() {
                                                                      
            @Override
            public Timetable addHour(String activity, String day) {
                if (!calendar.containsKey(day)){
                    calendar.put(day, new HashMap<>());
                    calendar.get(day).put(activity, 1);
                }else{
                    if (calendar.get(day).containsKey(activity)){
                        int c = calendar.get(day).get(activity);
                        calendar.get(day).put(activity, c + 1);
                    }
                }
                return this;
            }

            @Override
            public Set<String> activities() {
                Set<String> set = new HashSet<>();
                for (Map.Entry<String, Map<String, Integer>> entry : calendar.entrySet()) {
                    set.addAll(entry.getValue().keySet());
                }
                return set;
            }

            @Override
            public Set<String> days() {
               return calendar.keySet();
            }

            @Override
            public int getSingleData(String activity, String day) {
                int somma = 0;
                if (calendar.keySet().contains(day)){
                    if (calendar.get(day).keySet().contains(activity)){
                        for (Map.Entry<String, Integer> entry : calendar.get(day).entrySet()) {
                            if (entry.getKey().equals(activity)){
                                somma = somma + entry.getValue();
                            }
                        }
                    }
                }
                return somma;
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                int somma = 0;
                for (String day : days) {
                    if (calendar.keySet().contains(day)){
                        for (Map.Entry<String, Integer> entry : calendar.get(day).entrySet()) {
                            somma = somma + entry.getValue();
                        }
                    }      
                }
                return somma;    
            }
        };
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

    @Override
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }
    
}
