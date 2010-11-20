import java.text.DateFormat
import java.text.SimpleDateFormat

class DateUtils {
	static final DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
	static final DateFormat simpleFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, Locale.CHINESE)
	
    DateUtils() {
        // private
    }

    static String formatDate(Date date) {
        return simpleFormat.format(date)
    }

    static Date getLocalDate(Date date) {
        Calendar now = Calendar.instance
    	now.time = date
    	now.add(Calendar.HOUR, 8)
    	return now.time
    }
}