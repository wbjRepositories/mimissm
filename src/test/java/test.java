import com.wbj.utils.MD5Util;
import org.junit.Test;

public class test {
    @Test
    public void testM5(){
        String md5 = MD5Util.getMD5("000000");
        System.out.println(md5);
    }
}
