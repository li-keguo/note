import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * StringDemo.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class StringDemo {
    
    public static void main(String[] args) {
        final String str1 = "abcde";
        final String str2 = "fight";
        // magic
        exchange(str1, str2);
        System.out.println(str1 + " = " + str2);
    }
    
    
    @SuppressWarnings("all")
    public static void exchange(String str1, String str2) {
        try {
            final Field field = String.class.getDeclaredField("value");
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                public Object run() {
                    field.setAccessible(true);
                    return null;
                }
            });
            final char[] chars1 = (char[]) field.get(str1);
            final char[] chars2 = (char[]) field.get(str2);
            final char[] temp = new char[chars1.length];
            fill(temp, chars1);
            fill(chars1, chars2);
            fill(chars2, temp);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static void fill(char[] temp, char[] chars1) {
        for (int i = 0; i < temp.length; i++) {
            temp[i] = chars1[i];
        }
    }
}
