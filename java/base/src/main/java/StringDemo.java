import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;

/**
 * StringDemo.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class StringDemo {
    
    
    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "fight";
        // magic
        exchange(str1, str2);
        System.out.println(str1 + " = " + str2);
        
        final String s1 = "I'm s1";
        final String s2 = "I'm s2";
        // magic
        exchange(s1, s2);
        System.out.println(s1 + " = " + s2);
        
        final String s3 = "I'm s3";
        final String s4 = "I'm s4";
        // magic
        exchange(s3, s4);
        System.out.println(s3.toString() + " = " + s4.toString());
    }
    
    
    @SuppressWarnings("all")
    public static void exchange(String str1, String str2) {
        try {
            final Field field = getValueField();
            exchange((char[]) field.get(str1), (char[]) field.get(str2));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("all")
    private static Field getValueField() throws NoSuchFieldException {
        final Field field = String.class.getDeclaredField("value");
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            field.setAccessible(true);
            return null;
        });
        return field;
    }
    
    private static void exchange(char[] chars1, char[] chars2) {
        final char[] temp = new char[chars1.length];
        fill(temp, chars1);
        fill(chars1, chars2);
        fill(chars2, temp);
    }
    
    private static void fill(char[] temp, char[] chars1) {
        for (int i = 0; i < temp.length; i++) {
            temp[i] = chars1[i];
        }
    }
}
