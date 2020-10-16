import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class HelloClassloader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class clazz = new HelloClassloader().findClass("Hello");
            java.lang.reflect.Constructor<?> constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream in = HelloClassloader.class.getClassLoader().getResourceAsStream("Hello.xlass");
        byte[] bytes = new byte[0];
        try {
            bytes = new byte[in.available()];
            in.read(bytes);
            for (int i = 0; i<bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
