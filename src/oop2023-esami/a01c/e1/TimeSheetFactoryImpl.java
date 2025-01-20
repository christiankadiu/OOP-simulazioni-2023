package a01c.e1;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TimeSheetFactoryImpl implements TimeSheetFactory{

    @Override
    public TimeSheet ofRawData(List<Pair<String, String>> data) {
        return new TimeSheet() {

            @Override
            public Set<String> activities() {
                return data.stream().map(i -> i.get1()).collect(Collectors.toSet());
            }

            @Override
            public Set<String> days() {
                return data.stream().map(i -> i.get2()).collect(Collectors.toSet());
            }

            @Override
            public int getSingleData(String activity, String day) {
                return (int) data.stream().filter(i -> i.get1().equals(activity) && i.get2().equals(day)).count();
            }

            @Override
            public boolean isValid() {
                return true;
            }
            
        };
    }

    @Override
    public TimeSheet withBoundsPerActivity(List<Pair<String, String>> data, Map<String, Integer> boundsOnActivities) {
        return withBounds(data, boundsOnActivities, Map.of());
    }

    @Override
    public TimeSheet withBoundsPerDay(List<Pair<String, String>> data, Map<String, Integer> boundsOnDays) {
        return withBounds(data, Map.of(), boundsOnDays);
    }

    @Override
    public TimeSheet withBounds(List<Pair<String, String>> data, Map<String, Integer> boundsOnActivities,
            Map<String, Integer> boundsOnDays) {
        return new TimeSheet() {

            @Override
            public Set<String> activities() {
                return data.stream().map(i -> i.get1()).collect(Collectors.toSet());
            }

            @Override
            public Set<String> days() {
                return data.stream().map(i -> i.get2()).collect(Collectors.toSet());
            }

            @Override
            public int getSingleData(String activity, String day) {
                return (int) data.stream().filter(i -> i.get1().equals(activity) && i.get2().equals(day)).count();
            }

            @Override
            public boolean isValid() {
                for (Map.Entry<String, Integer> entry : boundsOnActivities.entrySet()) {
                    if((int) data.stream().filter(i -> i.get1().equals(entry.getKey())).count() > entry.getValue()){
                        return false;
                    }
                }
                for (Map.Entry<String, Integer> entry : boundsOnDays.entrySet()) {
                    if((int) data.stream().filter(i -> i.get2().equals(entry.getKey())).count() > entry.getValue()){
                        return false;
                    }
                }
                return true;
            }
            
        };
    }
    
}
