package a01b.e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.management.openmbean.ArrayType;

public class TimeSheetFactoryImpl implements TimeSheetFactory{

    @Override
    public TimeSheet flat(int numActivities, int numNames, int hours) {
        List<String> activities = new ArrayList<>();
        List<String> days = new ArrayList<>(); 
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 1; i <= numActivities; i++){
            activities.add("act"+i);
        }
        for (int i = 1; i <= numNames; i++){
            days.add("day"+i);
        }
        for (int i = 0; i < numActivities; i++){
            List<Integer> raw = new ArrayList<>();
            data.add(raw);
            for (int k = 0; k < numNames; k++){
                raw.add(hours);
            }
        }
        return ofListsOfLists(activities, days, data);
    }

    @Override
    public TimeSheet ofListsOfLists(List<String> activities, List<String> days, List<List<Integer>> data) {
        return new TimeSheet() {

            @Override
            public List<String> activities() {
                return activities;
            }

            @Override
            public List<String> days() {
                return days;
            }

            @Override
            public int getSingleData(String activity, String day) {
                int i = 0;
                int j = 0;
                if (activities.contains(activity) && days.contains(day)){
                    i = activities.indexOf(activity);
                    j = days.indexOf(day);
                    return data.get(i).get(j);
                }
                return 0;
            }

            @Override
            public Map<String, Integer> sumsPerActivity() {
                Map<String, Integer> mappa = new HashMap<>();
                for (int i = 0; i < activities.size(); i++){
                    int somma = 0;
                    for (int j = 0; j < data.get(i).size(); j++){
                        somma = somma + data.get(i).get(j);
                    }
                    mappa.put(activities.get(i), somma);
                }
                return mappa;
            }

            @Override
            public Map<String, Integer> sumsPerDay() {
                Map<String, Integer> mappa = new HashMap<>();
                for (int i = 0; i < days.size(); i++){
                    int somma = 0;
                    for (int j = 0; j < activities.size(); j++){
                        somma = somma + data.get(j).get(i);
                    }
                    mappa.put(days.get(i), somma);
                }
                return mappa;
            }
            
        };
    }

    @Override
    public TimeSheet ofRawData(int numActivities, int numDays, List<Pair<Integer, Integer>> data) {
        List<String> activities = new ArrayList<>();
        List<String> days = new ArrayList<>(); 
        List<List<Integer>> dat = new ArrayList<>();
        for (int i = 1; i <= numActivities; i++){
            activities.add("act"+i);
        }
        for (int i = 1; i <= numDays; i++){
            days.add("day"+i);
        }
        long rows = data.stream().map(i -> i.get1()).count();
        long cols = data.stream().map(i -> i.get1()).count();
        for (long i = 0l; i < rows; i++){
            List<Integer> raw = new ArrayList<>();
            dat.add(raw);
            for (long k = 0l; k < cols; k++){
                raw.add(0);
            }
        }
        for (Pair<Integer, Integer> p : data) {
            dat.get(p.get1()).set(p.get2(), dat.get(p.get1()).get(p.get2()) + 1);
        }
        return ofListsOfLists(activities, days, dat);
    }

    @Override
    public TimeSheet ofPartialMap(List<String> activities, List<String> days, Map<Pair<String, String>, Integer> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofPartialMap'");
    }
    
}
