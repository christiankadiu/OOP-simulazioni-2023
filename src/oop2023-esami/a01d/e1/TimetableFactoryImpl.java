package a01d.e1;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TimetableFactoryImpl implements TimetableFactory{

    private static record BookingSlot(String room, String course, Timetable.Day day, int hour){};

    private static record TimetableImpl(Set<BookingSlot> data) implements Timetable{

        @Override
        public Set<String> rooms() {
            return data.stream().map(i -> i.room).collect(Collectors.toSet());
        }

        @Override
        public Set<String> courses() {
            return data.stream().map(BookingSlot::course).collect(Collectors.toSet());
        }

        @Override
        public List<Integer> hours() {
            return data.stream().map(i -> i.hour).toList();
        }

        @Override
        public Timetable addBooking(String room, String course, Day day, int hour, int duration) {
            data.add(new BookingSlot(room, course, day, hour));
            return new TimetableImpl(data);
        }

        @Override
        public Optional<Integer> findPlaceForBooking(String room, Day day, int duration) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findPlaceForBooking'");
        }

        @Override
        public Map<Integer, String> getDayAtRoom(String room, Day day) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDayAtRoom'");
        }

        @Override
        public Optional<Pair<String, String>> getDayAndHour(Day day, int hour) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDayAndHour'");
        }

        @Override
        public Map<Day, Map<Integer, String>> getCourseTable(String course) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCourseTable'");
        }

    }

    @Override
    public Timetable empty() {
        return new TimetableImpl(Set.of());
    }
    
}
