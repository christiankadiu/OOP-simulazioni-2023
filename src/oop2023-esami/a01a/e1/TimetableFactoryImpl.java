package a01a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.List;

public class TimetableFactoryImpl implements TimetableFactory{

    @Override
    public Timetable empty() {
        return new Timetable() {

            Set<String> activities = new HashSet<>();
            Set<String> days = new HashSet<>();
            List<List<String>> data = new ArrayList<>();


            @Override
            public Timetable addHour(String activity, String day) {
                activities.add(activity);
                days.add(day);
                List<String> raw = new ArrayList<>();
                raw.add(day);
                data.add(raw);
                return this;
            }

            @Override
            public Set<String> activities() {
                return this.activities;
            }

            @Override
            public Set<String> days() {
                return this.days;
            }

            @Override
            public int getSingleData(String activity, String day) {
                return 1;
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                return 1;
            }
            
        };
    }

    @Override
    public Timetable single(String activity, String day) {
        return new Timetable() {

            @Override
            public Timetable addHour(String activity, String day) {
                return 
            }

            @Override
            public Set<String> activities() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activities'");
            }

            @Override
            public Set<String> days() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'days'");
            }

            @Override
            public int getSingleData(String activity, String day) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getSingleData'");
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'sums'");
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
