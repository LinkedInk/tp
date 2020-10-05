package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Date time format not accepted, the following are accepted:\n" +
                    "dd-MM-yyyy HH:mm";

    public final LocalDateTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param timeInput A valid time.
     */
    public Time(String timeInput) {
        requireNonNull(timeInput);
        checkArgument(isValidTime(timeInput), MESSAGE_CONSTRAINTS);
        this.time = parse(timeInput);
    }

    public static boolean isValidTime(String timeInput) {
        try {
            LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDateTime parse(String timeInput) {
        return LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
